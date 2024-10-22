package com.hend.ProjetMicroservice.services;

import com.hend.ProjetMicroservice.entities.Projet;

import java.util.List;

public interface IProjetService {
    Projet createProjet(Projet projet);
    Projet getProjetById(String id);
    List<Projet> getAllProjets();
    Projet updateProjet(String id, Projet projet);
    void deleteProjet(String id);
}
