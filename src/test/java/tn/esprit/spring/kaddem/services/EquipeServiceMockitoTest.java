package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Projet;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class EquipeServiceMockitoTest {

    @Mock
    EquipeRepository equipeRepository;

    @InjectMocks
    EquipeServiceImpl equipeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialisation manuelle des mocks
    }

    @Test
    public void testCalculerEfficaciteEquipeWithMockito() {
        // Arrange
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(1);
        Set<Projet> projets = new HashSet<>();

        Projet projet1 = new Projet();
        projet1.setBudgetDepasse(false);
        projet1.setEnRetard(false);

        Projet projet2 = new Projet();
        projet2.setBudgetDepasse(true); // Non-successful project

        Projet projet3 = new Projet();
        projet3.setBudgetDepasse(false);
        projet3.setEnRetard(false);

        projets.add(projet1);
        projets.add(projet2);
        projets.add(projet3);
        equipe.setProjets(projets);

        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));

        // Act
        double efficacite = equipeService.calculerEfficaciteEquipe(1);

        // Assert
        System.out.println("Calculated Efficacité: " + efficacite);
        assertEquals((2.0 / 3.0) * 100, efficacite);
    }

    @Test
    public void testCalculerEfficaciteEquipeWithNoProjects() {
        // Arrange
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(1);
        equipe.setProjets(new HashSet<>()); // No projects

        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));

        // Act
        double efficacite = equipeService.calculerEfficaciteEquipe(1);

        // Assert
        System.out.println("Calculated Efficacité: " + efficacite);
        assertEquals(0.0, efficacite);
    }

    @Test
    public void testCalculerEfficaciteEquipeWithAllSuccessfulProjects() {
        // Arrange
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(1);
        Set<Projet> projets = new HashSet<>();

        Projet projet1 = new Projet();
        projet1.setBudgetDepasse(false);
        projet1.setEnRetard(false);

        Projet projet2 = new Projet();
        projet2.setBudgetDepasse(false);
        projet2.setEnRetard(false);

        projets.add(projet1);
        projets.add(projet2);
        equipe.setProjets(projets);

        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));

        // Act
        double efficacite = equipeService.calculerEfficaciteEquipe(1);

        // Assert
        System.out.println("Calculated Efficacité: " + efficacite);
        assertEquals(100.0, efficacite);
    }

    @Test
    public void testCalculerEfficaciteEquipeWithAllUnsuccessfulProjects() {
        // Arrange
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(1);
        Set<Projet> projets = new HashSet<>();

        Projet projet1 = new Projet();
        projet1.setBudgetDepasse(true);
        projet1.setEnRetard(true);

        Projet projet2 = new Projet();
        projet2.setBudgetDepasse(true);
        projet2.setEnRetard(true);

        projets.add(projet1);
        projets.add(projet2);
        equipe.setProjets(projets);

        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));

        // Act
        double efficacite = equipeService.calculerEfficaciteEquipe(1);

        // Assert
        System.out.println("Calculated Efficacité: " + efficacite);
        assertEquals(0.0, efficacite);
    }

    @Test
    public void testCalculerEfficaciteEquipeWithMixedResults() {
        // Arrange
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(1);
        Set<Projet> projets = new HashSet<>();

        Projet projet1 = new Projet();
        projet1.setBudgetDepasse(false);
        projet1.setEnRetard(false); // Successful project

        Projet projet2 = new Projet();
        projet2.setBudgetDepasse(true); // Unsuccessful project

        Projet projet3 = new Projet();
        projet3.setBudgetDepasse(false);
        projet3.setEnRetard(false); // Successful project

        projets.add(projet1);
        projets.add(projet2);
        projets.add(projet3);
        equipe.setProjets(projets);

        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));

        // Act
        double efficacite = equipeService.calculerEfficaciteEquipe(1);

        // Assert
        System.out.println("Calculated Efficacité: " + efficacite);
        assertEquals((2.0 / 3.0) * 100, efficacite);
    }

    @Test
    public void testCalculerEfficaciteEquipeWithInvalidEquipeId() {
        // Arrange
        when(equipeRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            equipeService.calculerEfficaciteEquipe(1);
        });

        // Assert the exception message
        assertEquals("Equipe not found", thrown.getMessage());
    }



}
