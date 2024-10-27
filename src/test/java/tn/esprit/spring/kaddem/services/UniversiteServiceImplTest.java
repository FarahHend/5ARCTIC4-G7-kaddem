package tn.esprit.spring.kaddem.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

import java.util.*;

class UniversiteServiceImplTest {

    private static final Logger logger = LogManager.getLogger(UniversiteServiceImplTest.class);

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllUniversites() {
        logger.info("Starting testRetrieveAllUniversites");

        List<Universite> universites = Arrays.asList(new Universite(1, "University A"), new Universite(2, "University B"));
        when(universiteRepository.findAll()).thenReturn(universites);

        List<Universite> result = universiteService.retrieveAllUniversites();
        assertEquals(2, result.size());
        verify(universiteRepository, times(1)).findAll();

        logger.info("Completed testRetrieveAllUniversites - Result: {}", result.size());
    }

    @Test
    void testAddUniversite() {
        logger.info("Starting testAddUniversite");

        Universite universite = new Universite("New University");
        when(universiteRepository.save(any(Universite.class))).thenReturn(universite);

        Universite result = universiteService.addUniversite(universite);
        assertNotNull(result);
        assertEquals("New University", result.getNomUniv());
        verify(universiteRepository, times(1)).save(universite);

        logger.info("Completed testAddUniversite - Added University: {}", result.getNomUniv());
    }

    @Test
    void testUpdateUniversite() {
        logger.info("Starting testUpdateUniversite");

        Universite universite = new Universite(1, "Updated University");
        when(universiteRepository.save(any(Universite.class))).thenReturn(universite);

        Universite result = universiteService.updateUniversite(universite);
        assertNotNull(result);
        assertEquals("Updated University", result.getNomUniv());
        verify(universiteRepository, times(1)).save(universite);

        logger.info("Completed testUpdateUniversite - Updated University: {}", result.getNomUniv());
    }

    @Test
    void testRetrieveUniversite() {
        logger.info("Starting testRetrieveUniversite");

        Universite universite = new Universite(1, "Existing University");
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        Universite result = universiteService.retrieveUniversite(1);
        assertNotNull(result);
        assertEquals("Existing University", result.getNomUniv());
        verify(universiteRepository, times(1)).findById(1);

        logger.info("Completed testRetrieveUniversite - Retrieved University: {}", result.getNomUniv());
    }

    @Test
    void testDeleteUniversite() {
        logger.info("Starting testDeleteUniversite");

        Universite universite = new Universite(1, "Delete University");
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        universiteService.deleteUniversite(1);
        verify(universiteRepository, times(1)).delete(universite);

        logger.info("Completed testDeleteUniversite - Deleted University ID: {}", universite.getIdUniv());
    }

    @Test
    void testAssignUniversiteToDepartement() {
        logger.info("Starting testAssignUniversiteToDepartement");

        Universite universite = new Universite(1, "University with Dept");
        universite.setDepartements(new HashSet<>());
        Departement departement = new Departement();

        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        universiteService.assignUniversiteToDepartement(1, 1);

        assertTrue(universite.getDepartements().contains(departement));
        verify(universiteRepository, times(1)).save(universite);

        logger.info("Completed testAssignUniversiteToDepartement - Assigned Dept to University ID: {}", universite.getIdUniv());
    }

    @Test
    void testRetrieveDepartementsByUniversite() {
        logger.info("Starting testRetrieveDepartementsByUniversite");

        Set<Departement> departements = new HashSet<>();
        departements.add(new Departement());
        Universite universite = new Universite(1, "University with Dept");
        universite.setDepartements(departements);

        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        Set<Departement> result = universiteService.retrieveDepartementsByUniversite(1);
        assertEquals(departements, result);

        logger.info("Completed testRetrieveDepartementsByUniversite - Retrieved Departements Count: {}", result.size());
    }

    @Test
    void testCountDepartementsInUniversite() {
        logger.info("Starting testCountDepartementsInUniversite");

        Set<Departement> departements = new HashSet<>();
        departements.add(new Departement());
        Universite universite = new Universite(1, "University with Dept");
        universite.setDepartements(departements);

        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        int result = universiteService.countDepartementsInUniversite(1);
        assertEquals(1, result);

        logger.info("Completed testCountDepartementsInUniversite - Count: {}", result);
    }

    @Test
    void testDeleteAllDepartementsFromUniversite() {
        logger.info("Starting testDeleteAllDepartementsFromUniversite");

        Set<Departement> departements = new HashSet<>();
        departements.add(new Departement());
        Universite universite = new Universite(1, "University with Dept");
        universite.setDepartements(departements);

        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        universiteService.deleteAllDepartementsFromUniversite(1);

        assertTrue(universite.getDepartements().isEmpty());
        verify(universiteRepository, times(1)).save(universite);

        logger.info("Completed testDeleteAllDepartementsFromUniversite - Remaining Departements Count: {}", universite.getDepartements().size());
    }
}
