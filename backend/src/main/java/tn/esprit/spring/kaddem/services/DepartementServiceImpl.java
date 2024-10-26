package tn.esprit.spring.kaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Slf4j

@Service
public class DepartementServiceImpl implements IDepartementService{
	private static final Logger logger = LogManager.getLogger(DepartementServiceImpl.class);

	@Autowired
	DepartementRepository departementRepository;
	public List<Departement> retrieveAllDepartements(){

		logger.info("Début de la récupération de tous les départements depuis la base de données.");
		try {
			List<Departement> departements = (List<Departement>) departementRepository.findAll();
			logger.info("Récupération réussie de tous les départements. Nombre total : {}", departements.size());
			return departements;
		} catch (Exception e) {
			logger.error("Erreur lors de la récupération des départements depuis la base de données", e);
			throw e;
		}
	}

	public Departement addDepartement (Departement d){

		logger.info("Début de l'ajout d'un nouveau département : {}", d.getNomDepart());
		try {
			Departement savedDepartement = departementRepository.save(d);
			logger.info("Département sauvegardé dans la base de données avec succès : {}", savedDepartement.getNomDepart());
			return savedDepartement;
		} catch (Exception e) {
			logger.error("Erreur lors de la sauvegarde du département dans la base de données : {}", d.getNomDepart(), e);
			throw e;
		}
	}

	public   Departement updateDepartement (Departement d){
		logger.info("Début de la mise à jour du département avec ID : {}", d.getIdDepart());
		try {
			Departement updatedDepartement = departementRepository.save(d);
			logger.info("Département sauvegardé avec succès dans la base de données : {}", updatedDepartement.getNomDepart());
			return updatedDepartement;
		} catch (Exception e) {
			logger.error("Erreur lors de la mise à jour du département avec ID : {}", d.getNomDepart(), e);
			throw e;
		}
	}

	public  Departement retrieveDepartement (Integer idDepart){

		logger.info("Récupération du département avec ID : {}", idDepart);
		try {
			Departement departement = departementRepository.findById(idDepart)
					.orElseThrow(() -> new RuntimeException("Département non trouvé : ID " + idDepart));
			logger.info("Département récupéré avec succès : {}", departement.getNomDepart());
			return departement;
		} catch (Exception e) {
			logger.error("Erreur lors de la récupération du département avec ID : {}", idDepart, e);
			throw e;
		}
	}

	public  void deleteDepartement(Integer idDepartement){
		logger.warn("Suppression du département avec ID : {}", idDepartement);
		try {
			departementRepository.deleteById(idDepartement);
			logger.info("Département supprimé avec succès : ID {}", idDepartement);
		} catch (Exception e) {
			logger.error("Erreur lors de la suppression du département avec ID : {}", idDepartement, e);
			throw e;
		}
	}

}
