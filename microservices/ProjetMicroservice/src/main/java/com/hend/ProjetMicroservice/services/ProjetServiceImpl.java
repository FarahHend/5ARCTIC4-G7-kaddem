package com.hend.ProjetMicroservice.services;

import com.hend.ProjetMicroservice.entities.Projet;
import com.hend.ProjetMicroservice.repositories.ProjetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetServiceImpl implements IProjetService {

    @Autowired
    private ProjetRepo projetRepository;

    @Override
    public Projet createProjet(Projet projet) {
        return projetRepository.save(projet);
    }

    @Override
    public Projet getProjetById(String id) {
        Optional<Projet> projetOptional = projetRepository.findById(id);
        return projetOptional.orElse(null);
    }

    @Override
    public List<Projet> getAllProjets() {
        return projetRepository.findAll();
    }

    @Override
    public Projet updateProjet(String id, Projet projet) {
        // Check if the projet exists
        if (projetRepository.existsById(id)) {
            projet.setIdProjet(id);
            return projetRepository.save(projet);
        } else {
            return null;
        }
    }

    @Override
    public void deleteProjet(String id) {
        projetRepository.deleteById(id);
    }
}
