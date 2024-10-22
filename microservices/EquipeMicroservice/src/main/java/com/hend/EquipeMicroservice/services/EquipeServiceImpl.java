package com.hend.EquipeMicroservice.services;

import com.hend.EquipeMicroservice.entities.Equipe;
import com.hend.EquipeMicroservice.entities.ProjetResponse;
import com.hend.EquipeMicroservice.repositories.EquipeRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class EquipeServiceImpl implements IEquipeService {
    private final EquipeRepo equipeRepository;
    @Autowired
    private ProjetFeignClient projetFeignClient;

    public List<Equipe> retrieveAllEquipes() {
        log.info("Retrieving all equipes");
        List<Equipe> equipes = (List<Equipe>) equipeRepository.findAll();
        log.debug("Retrieved equipes: {}", equipes);
        return equipes;
    }

    public Equipe addEquipe(Equipe e) {
        log.info("Adding equipe: {}", e);
        Equipe savedEquipe = equipeRepository.save(e);
        log.info("Added equipe: {}", savedEquipe);
        return savedEquipe;
    }

    public void deleteEquipe(Integer idEquipe) {
        log.info("Deleting equipe with ID: {}", idEquipe);
        Equipe e = retrieveEquipe(idEquipe);
        if (e != null) {
            equipeRepository.delete(e);
            log.info("Deleted equipe: {}", e);
        } else {
            log.warn("Equipe with ID: {} not found", idEquipe);
        }
    }

    public Equipe retrieveEquipe(Integer idEquipe) {
        log.info("Retrieving equipe with ID: {}", idEquipe);
        Equipe equipe = equipeRepository.findById(idEquipe).orElse(null);
        if (equipe != null) {
            log.info("Found equipe: {}", equipe);
        } else {
            log.warn("Equipe with ID: {} not found", idEquipe);
        }
        return equipe;
    }

    public Equipe updateEquipe(Integer id, Equipe e) {
        log.info("Updating equipe with ID: {}", id);

        // Fetch the existing equipe from the repository
        Equipe existingEquipe = equipeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipe not found with ID: " + id));

        // Update the existing equipe's fields
        existingEquipe.setNomEquipe(e.getNomEquipe());
        existingEquipe.setNiveau(e.getNiveau());
        // Update any other fields as necessary...

        // Save the updated equipe
        Equipe savedEquipe = equipeRepository.save(existingEquipe);

        log.info("Updated equipe: {}", savedEquipe);
        return savedEquipe;
    }

    @Override
    public double calculerEfficaciteEquipe(Integer idEquipe) {
        log.info("Calculating efficiency for equipe ID: {}", idEquipe);
        Equipe equipe = retrieveEquipe(idEquipe);
        if (equipe == null) {
            log.error("Equipe not found with ID: {}", idEquipe);
            throw new RuntimeException("Equipe not found");
        }

        List<String> projetIds = equipe.getProjetIds();

        List<ProjetResponse> projets = projetIds.stream()
                .map(projetId -> projetFeignClient.getProjetById(projetId))
                .filter(projet -> projet != null)
                .collect(Collectors.toList());

        int totalProjets = projets.size();
        int projetsReussis = (int) projets.stream()
                .filter(projet -> !projet.isBudgetDepasse() && !projet.isEnRetard())
                .count();

        double efficiency = totalProjets == 0 ? 0 : (double) projetsReussis / totalProjets * 100;
        log.info("Calculated efficiency for equipe ID {}: {}%", idEquipe, efficiency);
        return efficiency;
    }
}
