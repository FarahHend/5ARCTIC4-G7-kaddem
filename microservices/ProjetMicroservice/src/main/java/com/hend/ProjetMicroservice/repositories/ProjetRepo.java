package com.hend.ProjetMicroservice.repositories;

import com.hend.ProjetMicroservice.entities.Projet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetRepo extends MongoRepository<Projet, String> {
    Projet findByNomProjet(String nomProjet);
}
