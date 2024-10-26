package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.*;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EtudiantServiceImplTestJUnit {

    private EtudiantServiceImpl etudiantService;
    private EtudiantRepository etudiantRepository;
    private ContratRepository contratRepository;
    private EquipeRepository equipeRepository;
    private DepartementRepository departementRepository;

    @BeforeEach
    void setUp() {
        etudiantRepository = new EtudiantRepositoryStub(); // Use your own implementation
        contratRepository = new ContratRepositoryStub(); // Use your own implementation
        equipeRepository = new EquipeRepositoryStub(); // Use your own implementation
        departementRepository = new DepartementRepositoryStub(); // Use your own implementation

        etudiantService = new EtudiantServiceImpl();
        etudiantService.etudiantRepository = etudiantRepository;
        etudiantService.contratRepository = contratRepository;
        etudiantService.equipeRepository = equipeRepository;
        etudiantService.departementRepository = departementRepository;
    }

    @Test
    void retrieveAllEtudiants() {
        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();
        assertNotNull(etudiants);
        assertTrue(etudiants.isEmpty());

        etudiantRepository.save(new Etudiant());
        etudiantRepository.save(new Etudiant());

        etudiants = etudiantService.retrieveAllEtudiants();
        assertEquals(2, etudiants.size());
    }

    @Test
    void addEtudiant() {
        Etudiant etudiant = new Etudiant();
        Etudiant result = etudiantService.addEtudiant(etudiant);
        assertNotNull(result);
        assertEquals(etudiant, result);
    }

    @Test
    void updateEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiantRepository.save(etudiant); // Use save instead of addEtudiant

        etudiant.setNomE("Updated Name");
        Etudiant result = etudiantService.updateEtudiant(etudiant);
        assertNotNull(result);
        assertEquals("Updated Name", result.getNomE());
    }

    @Test
    void retrieveEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiantRepository.save(etudiant); // Use save instead of addEtudiant

        Etudiant result = etudiantService.retrieveEtudiant(1);
        assertNotNull(result);
        assertEquals(etudiant, result);
    }

    @Test
    void removeEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiantRepository.save(etudiant); // Use save instead of addEtudiant

        etudiantService.removeEtudiant(1);
        assertThrows(Exception.class, () -> etudiantService.retrieveEtudiant(1));
    }



    @Test
    void addAndAssignEtudiantToEquipeAndContract() {
        Etudiant etudiant = new Etudiant();
        Contrat contrat = new Contrat();
        Equipe equipe = new Equipe();
        equipe.setEtudiants(new HashSet<>());

        contratRepository.save(contrat); // Use save instead of addContrat
        equipeRepository.save(equipe); // Use save instead of addEquipe
        etudiantService.addEtudiant(etudiant);

        Etudiant result = etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant, contrat.getIdContrat(), equipe.getIdEquipe());

        assertEquals(etudiant, contrat.getEtudiant());
        assertTrue(equipe.getEtudiants().contains(etudiant));
    }



}
