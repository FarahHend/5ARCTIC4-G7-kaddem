package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.services.IDepartementService;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@AllArgsConstructor
@RequestMapping("/departement")
public class DepartementRestController {
	private static final Logger logger = LogManager.getLogger(DepartementRestController.class);

	IDepartementService departementService;
	// http://localhost:8089/Kaddem/departement/retrieve-all-departements
	@GetMapping("/retrieve-all-departements")
	public ResponseEntity<List<Departement>> getDepartements() {
		logger.info("Requête GET reçue pour récupérer tous les départements");
		try {
			List<Departement> departements = departementService.retrieveAllDepartements();
			logger.info("Nombre de départements récupérés : {}", departements.size());
			return ResponseEntity.ok(departements);
		} catch (Exception e) {
			logger.error("Erreur lors de la récupération des départements", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	// http://localhost:8089/Kaddem/departement/retrieve-departement/8
	@GetMapping("/retrieve-departement/{departement-id}")
	public ResponseEntity<Departement> retrieveDepartement(@PathVariable("departement-id") Integer departementId) {
		logger.info("Requête GET reçue pour récupérer le département avec ID : {}", departementId);
		try {
			Departement departement = departementService.retrieveDepartement(departementId);
			logger.info("Département récupéré avec succès : {}", departement.getNomDepart());
			return ResponseEntity.ok(departement);
		} catch (Exception e) {
			logger.error("Erreur lors de la récupération du département avec ID : {}", departementId, e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	// http://localhost:8089/Kaddem/departement/add-departement
	@PostMapping("/add-departement")
	public ResponseEntity<Departement> addDepartement(@RequestBody Departement d) {
		logger.info("Requête POST reçue pour ajouter un département : {}", d.getNomDepart());
		try {
			Departement newDepartement = departementService.addDepartement(d);
			logger.info("Département ajouté avec succès : {}", newDepartement.getNomDepart());
			return ResponseEntity.status(HttpStatus.CREATED).body(newDepartement);
		} catch (Exception e) {
			logger.error("Erreur lors de l'ajout du département : {}", d.getNomDepart(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// http://localhost:8089/Kaddem/departement/remove-departement/1
	@DeleteMapping("/remove-departement/{departement-id}")
	public ResponseEntity<Void> removeDepartement(@PathVariable("departement-id") Integer departementId) {
		logger.warn("Requête DELETE reçue pour supprimer le département avec ID : {}", departementId);
		try {
			departementService.deleteDepartement(departementId);
			logger.info("Département supprimé avec succès : ID {}", departementId);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			logger.error("Erreur lors de la suppression du département avec ID : {}", departementId, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// http://localhost:8089/Kaddem/departement/update-departement
	@PutMapping("/update-departement")
	public ResponseEntity<Departement> updateDepartement(@RequestBody Departement d) {
		logger.info("Requête PUT reçue pour mettre à jour le département avec ID : {}", d.getIdDepart());
		try {
			Departement updatedDepartement = departementService.updateDepartement(d);
			logger.info("Département mis à jour avec succès : {}", updatedDepartement.getNomDepart());
			return ResponseEntity.ok(updatedDepartement);
		} catch (Exception e) {
			logger.error("Erreur lors de la mise à jour du département avec ID : {}", d.getIdDepart(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}


