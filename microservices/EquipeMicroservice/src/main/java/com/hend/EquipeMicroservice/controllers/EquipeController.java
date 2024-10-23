package com.hend.EquipeMicroservice.controllers;

import com.hend.EquipeMicroservice.entities.Equipe;
import com.hend.EquipeMicroservice.services.IEquipeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/equipe")
public class EquipeController {
    IEquipeService equipeService;
    // http://localhost:8222/equipe/retrieve-all-equipes
    @GetMapping("/retrieve-all-equipes")
    public List<Equipe> getEquipes() {
        List<Equipe> listEquipes = equipeService.retrieveAllEquipes();
        return listEquipes;
    }
    // http://localhost:8222/equipe/retrieve-equipe/8
    @GetMapping("/retrieve-equipe/{idEquipe}")
    public Equipe retrieveEquipe(@PathVariable("idEquipe") Integer equipeId) {
        return equipeService.retrieveEquipe(equipeId);
    }

    // http://localhost:8222/equipe/add-equipe
    @PostMapping("/add-equipe")
    public Equipe addEquipe(@RequestBody Equipe e) {
        Equipe equipe = equipeService.addEquipe(e);
        return equipe;
    }

    // http://localhost:8222/equipe/remove-equipe/1
    @DeleteMapping("/remove-equipe/{idEquipe}")
    public void removeEquipe(@PathVariable("idEquipe") Integer equipeId) {
        equipeService.deleteEquipe(equipeId);
    }

    // http://localhost:8222/equipe/update-equipe
    @PutMapping("/update-equipe/{idEquipe}")
    public Equipe updateEquipe(@PathVariable Integer idEquipe, @RequestBody Equipe e) {
        Equipe updatedEquipe = equipeService.updateEquipe(idEquipe, e);
        return updatedEquipe;
    }

    //@Scheduled(cron="0 0 13 * * *")
    //@PutMapping("/faireEvoluerEquipes")
    //public void faireEvoluerEquipes() {
        //equipeService.evoluerEquipes() ;
   // }

    @GetMapping("/efficacite/{idEquipe}")
    public ResponseEntity<Double> calculerEfficacite(@PathVariable Integer idEquipe) {
        try {
            double efficacite = equipeService.calculerEfficaciteEquipe(idEquipe);
            return new ResponseEntity<>(efficacite, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}


