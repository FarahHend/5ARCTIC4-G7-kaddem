package tn.esprit.spring.kaddem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.kaddem.entities.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.kaddem.entities.Equipe;

public interface EquipeRepository extends CrudRepository<Equipe, Integer> {
    // Additional custom query methods can be defined here if needed
}

