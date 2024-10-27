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
        // Préparer les données
        Departement departement = new Departement();
        departement.setNomDepart("Informatique");

        // Définir le comportement du mock
        when(departementRepository.save(any(Departement.class))).thenReturn(departement);

        // Appeler la méthode testée
        Departement result = departementService.addDepartement(departement);

        // Vérifier les résultats
        assertNotNull(result, "Le résultat ne doit pas être null");
        assertEquals("Informatique", result.getNomDepart(), "Le nom du département doit être 'Informatique'");
        verify(departementRepository, times(1)).save(departement);
    }
}
