package tn.esprit.spring.kaddem.services;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DepartementServiceImplTest {
    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private DepartementServiceImpl departementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddDepartment() {

        Departement departement = new Departement();
        departement.setNomDepart("Informatique");

        when(departementRepository.save(any(Departement.class))).thenReturn(departement);

        Departement result = departementService.addDepartement(departement);

        assertNotNull(result, "Le résultat ne doit pas être null");
        assertEquals("Informatique", result.getNomDepart(), "Le nom du département doit être 'Informatique'");
        verify(departementRepository, times(1)).save(departement);
    }

    @Test
    void testRetrieveAllDepartments() {
        List<Departement> departements = Arrays.asList(new Departement("Informatique"), new Departement("Mathématiques"));
        when(departementRepository.findAll()).thenReturn(departements);

        List<Departement> result = departementService.retrieveAllDepartements();

        assertEquals(2, result.size());
        verify(departementRepository, times(1)).findAll();
    }

    @Test
    void testUpdateDepartment() {
        Departement departement = new Departement("Informatique");
        departement.setIdDepart(1);
        when(departementRepository.save(departement)).thenReturn(departement);

        Departement result = departementService.updateDepartement(departement);

        assertEquals("Informatique", result.getNomDepart());
        verify(departementRepository, times(1)).save(departement);
    }

    @Test
    void testDeleteDepartment() {
        Integer departementId = 1;
        doNothing().when(departementRepository).deleteById(departementId);

        departementService.deleteDepartement(departementId);

        verify(departementRepository, times(1)).deleteById(departementId);
    }

    @Test
    void testRetrieveDepartement_Success() {
        // Préparer les données
        Integer departementId = 1;
        Departement departement = new Departement();
        departement.setIdDepart(departementId);
        departement.setNomDepart("Informatique");

        // Définir le comportement du mock pour retourner le département
        when(departementRepository.findById(departementId)).thenReturn(Optional.of(departement));

        // Appeler la méthode à tester
        Departement result = departementService.retrieveDepartement(departementId);

        // Vérifications
        assertNotNull(result, "Le département ne doit pas être null");
        assertEquals("Informatique", result.getNomDepart(), "Le nom du département doit être 'Informatique'");
        verify(departementRepository, times(1)).findById(departementId);
    }

    @Test
    void testRetrieveDepartement_NotFound() {
        // Préparer les données
        Integer departementId = 1;

        // Définir le comportement du mock pour retourner un département non trouvé
        when(departementRepository.findById(departementId)).thenReturn(Optional.empty());

        // Appeler la méthode et vérifier qu'une exception est lancée
        Exception exception = assertThrows(RuntimeException.class, () -> {
            departementService.retrieveDepartement(departementId);
        });

        assertEquals("Département non trouvé : ID 1", exception.getMessage());
        verify(departementRepository, times(1)).findById(departementId);
    }




}
