package tn.esprit.spring.kaddem.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.*;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class EquipeServiceImpl implements IEquipeService {
	private final EquipeRepository equipeRepository;

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

	public Equipe updateEquipe(Equipe e) {
		log.info("Updating equipe: {}", e);
		Equipe updatedEquipe = equipeRepository.save(e);
		log.info("Updated equipe: {}", updatedEquipe);
		return updatedEquipe;
	}

	public void evoluerEquipes() {
		log.info("Evolving equipes");
		List<Equipe> equipes = (List<Equipe>) equipeRepository.findAll();
		for (Equipe equipe : equipes) {
			if ((equipe.getNiveau().equals(Niveau.JUNIOR)) || (equipe.getNiveau().equals(Niveau.SENIOR))) {
				List<Etudiant> etudiants = (List<Etudiant>) equipe.getEtudiants();
				Integer nbEtudiantsAvecContratsActifs = 0;
				for (Etudiant etudiant : etudiants) {
					Set<Contrat> contrats = etudiant.getContrats();
					for (Contrat contrat : contrats) {
						Date dateSysteme = new Date();
						long difference_In_Time = dateSysteme.getTime() - contrat.getDateFinContrat().getTime();
						long difference_In_Years = (difference_In_Time / (1000L * 60 * 60 * 24 * 365));
						if (!contrat.getArchive() && difference_In_Years > 1) {
							nbEtudiantsAvecContratsActifs++;
							log.debug("Active contract found for student: {}", etudiant);
							break;
						}
						if (nbEtudiantsAvecContratsActifs >= 3) break;
					}
				}
				if (nbEtudiantsAvecContratsActifs >= 3) {
					if (equipe.getNiveau().equals(Niveau.JUNIOR)) {
						equipe.setNiveau(Niveau.SENIOR);
						equipeRepository.save(equipe);
						log.info("Upgraded equipe from JUNIOR to SENIOR: {}", equipe);
						break;
					}
					if (equipe.getNiveau().equals(Niveau.SENIOR)) {
						equipe.setNiveau(Niveau.EXPERT);
						equipeRepository.save(equipe);
						log.info("Upgraded equipe from SENIOR to EXPERT: {}", equipe);
						break;
					}
				}
			}
		}
	}

	@Override
	public double calculerEfficaciteEquipe(Integer idEquipe) {
		log.info("Calculating efficiency for equipe ID: {}", idEquipe);
		Equipe equipe = retrieveEquipe(idEquipe);
		if (equipe == null) {
			log.error("Equipe not found with ID: {}", idEquipe);
			throw new RuntimeException("Equipe not found");
		}

		int totalProjets = equipe.getProjets().size();
		int projetsReussis = (int) equipe.getProjets().stream()
				.filter(projet -> !projet.isBudgetDepasse() && !projet.isEnRetard())
				.count();
		double efficiency = totalProjets == 0 ? 0 : (double) projetsReussis / totalProjets * 100;
		log.info("Calculated efficiency for equipe ID {}: {}%", idEquipe, efficiency);
		return efficiency;
	}
}
