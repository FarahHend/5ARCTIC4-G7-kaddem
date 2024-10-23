package com.hend.ProjetMicroservice.controllers;

import com.hend.ProjetMicroservice.entities.Projet;
import com.hend.ProjetMicroservice.services.IProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/projet")
public class ProjetController {

    @Autowired
    private IProjetService projetService;

    @PostMapping
    public ResponseEntity<Projet> createProjet(@RequestBody Projet projet) {
        Projet createdProjet = projetService.createProjet(projet);
        return ResponseEntity.ok(createdProjet);
    }

    @GetMapping("/{idProjet}")
    public ResponseEntity<Projet> getProjetById(@PathVariable String idProjet) {
        Projet projet = projetService.getProjetById(idProjet);
        return projet != null ? ResponseEntity.ok(projet) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Projet>> getAllProjets() {
        List<Projet> projets = projetService.getAllProjets();
        return ResponseEntity.ok(projets);
    }

    @PutMapping("/{idProjet}")
    public ResponseEntity<Projet> updateProjet(@PathVariable String idProjet, @RequestBody Projet projet) {
        Projet updatedProjet = projetService.updateProjet(idProjet, projet);
        return updatedProjet != null ? ResponseEntity.ok(updatedProjet) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idProjet}")
    public ResponseEntity<Void> deleteProjet(@PathVariable String idProjet) {
        projetService.deleteProjet(idProjet);
        return ResponseEntity.noContent().build();
    }
}
