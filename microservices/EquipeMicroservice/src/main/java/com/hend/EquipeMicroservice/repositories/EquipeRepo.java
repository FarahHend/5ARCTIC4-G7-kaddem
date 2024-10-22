package com.hend.EquipeMicroservice.repositories;

import com.hend.EquipeMicroservice.entities.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipeRepo extends JpaRepository<Equipe, Integer> {
    Equipe findByNomEquipe(String nomEquipe);
    Optional<Equipe> findById(Integer id);
}
