package com.hend.EquipeMicroservice;

import com.hend.EquipeMicroservice.entities.Equipe;
import com.hend.EquipeMicroservice.entities.ProjetResponse;
import com.hend.EquipeMicroservice.repositories.EquipeRepo;
import com.hend.EquipeMicroservice.services.EquipeServiceImpl;
import com.hend.EquipeMicroservice.services.ProjetFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestPropertySource(properties = "application.config.projet-url=http://192.168.33.10:8055")
class EquipeServiceImplTest {

    @InjectMocks
    private EquipeServiceImpl equipeService;

    @Mock
    private EquipeRepo equipeRepository;

    @Mock
    private ProjetFeignClient projetFeignClient;

    private Equipe equipe;

    @BeforeEach
    void setUp() {
        equipe = new Equipe();
        equipe.setProjetIds(Arrays.asList("projet1", "projet2"));
    }

    @Test
    void testCalculerEfficaciteEquipe_EquipeNotFound() {
        // Arrange
        when(equipeRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            equipeService.calculerEfficaciteEquipe(1);
        });

        assertEquals("Equipe not found", exception.getMessage());
    }

    @Test
    void testCalculerEfficaciteEquipe_WithMixedProjectResults() {
        // Arrange
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));
        when(projetFeignClient.getProjetById("projet1")).thenReturn(new ProjetResponse("projet1", true , true));
        when(projetFeignClient.getProjetById("projet2")).thenReturn(new ProjetResponse("projet2", false , false));

        // Act
        double result = equipeService.calculerEfficaciteEquipe(1);

        // Assert
        assertEquals(50.0, result, 0.01);
    }


    @Test
    void testCalculerEfficaciteEquipe_AllProjectsSuccessful() {
        // Arrange
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));
        when(projetFeignClient.getProjetById("projet1")).thenReturn(new ProjetResponse("projet1", false , false));
        when(projetFeignClient.getProjetById("projet2")).thenReturn(new ProjetResponse("projet2", false , false));

        // Act
        double result = equipeService.calculerEfficaciteEquipe(1);

        // Assert
        assertEquals(100.0, result, 0.01);
    }

    @Test
    void testCalculerEfficaciteEquipe_AllProjectsFailed() {
        // Arrange
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));
        when(projetFeignClient.getProjetById("projet1")).thenReturn(new ProjetResponse("projet1", true, true));
        when(projetFeignClient.getProjetById("projet2")).thenReturn(new ProjetResponse("projet2", true , true));

        // Act
        double result = equipeService.calculerEfficaciteEquipe(1);

        // Assert
        assertEquals(0.0, result, 0.01);
    }

    @Test
    void testCalculerEfficaciteEquipe_SingleSuccessfulProject() {
        // Arrange
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));
        when(projetFeignClient.getProjetById("projet1")).thenReturn(new ProjetResponse("projet1", true , true));
        when(projetFeignClient.getProjetById("projet2")).thenReturn(new ProjetResponse("projet2", false , false));

        // Act
        double result = equipeService.calculerEfficaciteEquipe(1);

        // Assert
        assertEquals(50.0, result, 0.01);
    }

    @Test
    void testCalculerEfficaciteEquipe_EmptyProjectList() {
        // Arrange
        equipe.setProjetIds(Arrays.asList());
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));

        // Act
        double result = equipeService.calculerEfficaciteEquipe(1);

        // Assert
        assertEquals(0.0, result, 0.01);
    }


}
