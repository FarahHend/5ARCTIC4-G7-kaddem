package com.hend.EquipeMicroservice;

import com.hend.EquipeMicroservice.entities.Equipe;
import com.hend.EquipeMicroservice.entities.ProjetResponse;
import com.hend.EquipeMicroservice.repositories.EquipeRepo;
import com.hend.EquipeMicroservice.services.EquipeServiceImpl;
import com.hend.EquipeMicroservice.services.ProjetFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        MockitoAnnotations.openMocks(this);
        equipe = new Equipe();
        equipe.setIdEquipe(1);
        equipe.setProjetIds(Arrays.asList("projet1", "projet2"));
    }

    @Test
    void testCalculerEfficaciteEquipe_Success() {
        // Arrange
        when(equipeRepository.findById(1)).thenReturn(java.util.Optional.of(equipe));

        ProjetResponse projet1 = new ProjetResponse();
        projet1.setBudgetDepasse(false);
        projet1.setEnRetard(false);

        ProjetResponse projet2 = new ProjetResponse();
        projet2.setBudgetDepasse(true);
        projet2.setEnRetard(false);

        when(projetFeignClient.getProjetById("projet1")).thenReturn(projet1);
        when(projetFeignClient.getProjetById("projet2")).thenReturn(projet2);

        // Act
        double efficiency = equipeService.calculerEfficaciteEquipe(1);

        // Assert
        assertEquals(50.0, efficiency, 0.01);
    }

    @Test
    void testCalculerEfficaciteEquipe_NoProjets() {
        // Arrange
        equipe.setProjetIds(Collections.emptyList());
        when(equipeRepository.findById(1)).thenReturn(java.util.Optional.of(equipe));

        // Act
        double efficiency = equipeService.calculerEfficaciteEquipe(1);

        // Assert
        assertEquals(0.0, efficiency);
    }

    @Test
    void testCalculerEfficaciteEquipe_EquipeNotFound() {
        // Arrange
        when(equipeRepository.findById(1)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            equipeService.calculerEfficaciteEquipe(1);
        });
        assertEquals("Equipe not found", exception.getMessage());
    }

    @Test
    void testCalculerEfficaciteEquipe_AllSuccessfulProjets() {
        // Arrange
        when(equipeRepository.findById(1)).thenReturn(java.util.Optional.of(equipe));

        ProjetResponse projet1 = new ProjetResponse();
        projet1.setBudgetDepasse(false);
        projet1.setEnRetard(false);

        ProjetResponse projet2 = new ProjetResponse();
        projet2.setBudgetDepasse(false);
        projet2.setEnRetard(false);

        when(projetFeignClient.getProjetById("projet1")).thenReturn(projet1);
        when(projetFeignClient.getProjetById("projet2")).thenReturn(projet2);

        // Act
        double efficiency = equipeService.calculerEfficaciteEquipe(1);

        // Assert
        assertEquals(100.0, efficiency, 0.01);
    }

    @Test
    void testCalculerEfficaciteEquipe_AllFailedProjets() {
        // Arrange
        when(equipeRepository.findById(1)).thenReturn(java.util.Optional.of(equipe));

        ProjetResponse projet1 = new ProjetResponse();
        projet1.setBudgetDepasse(true);
        projet1.setEnRetard(true);

        ProjetResponse projet2 = new ProjetResponse();
        projet2.setBudgetDepasse(true);
        projet2.setEnRetard(true);

        when(projetFeignClient.getProjetById("projet1")).thenReturn(projet1);
        when(projetFeignClient.getProjetById("projet2")).thenReturn(projet2);

        // Act
        double efficiency = equipeService.calculerEfficaciteEquipe(1);

        // Assert
        assertEquals(0.0, efficiency);
    }
}
