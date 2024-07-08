package fr.diginamic.spring_demo.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.spring_demo.entity.DepartementTp6;
import fr.diginamic.spring_demo.entity.VilleTp6;
import fr.diginamic.spring_demo.repositories.VilleRepository;
import fr.diginamic.spring_demo.utilitaire.PopComparateur;

@Service
public class VilleService {

	@Autowired
	private VilleRepository villeRepository;

	@Autowired
	private DepartementService departementService;

	public Iterable<VilleTp6> extractVilles() {
		return villeRepository.findAll();
//		return villes.stream().map(v -> convertirVilleDto(v)).collect(Collectors.toList());
	}

//	public VilleDTO convertirVilleDto(VilleTp6 ville) {
//		if (ville != null) {
//			return new VilleDTO(ville.getId(), ville.getNom(), ville.getDepartement().getNom());
//		}
//		return null;
//	}

	public void addDataToBase(List<VilleTp6> villeListe) {

		Collections.sort(villeListe, new PopComparateur(false));
		int limite = 1000;
		final int[] compteur = { 0 };

		villeListe.stream().limit(1007).forEach(v -> {
			if (compteur[0] < limite) {
				int result = insertVille(v);
				if (result > 0) {
					compteur[0]++;
				}
			}
		});
	}

	public VilleTp6 findById(int idVille) {
		return villeRepository.findById(idVille).orElse(null);
	}

	public VilleTp6 findByNom(String nomVille) {
		return villeRepository.findByNom(nomVille);
	}

	public List<VilleTp6> findByNomStartingWith(String nomVille) {
		return villeRepository.findByNomStartingWith(nomVille);
	}

	public List<VilleTp6> findByNbHabitantsGreaterThan(int popMin) {
		return villeRepository.findByNbHabitantsGreaterThan(popMin);
	}

	public List<VilleTp6> findByNbHabitantsBetween(int popMin, int popMax) {
		return villeRepository.findByNbHabitantsBetween(popMin, popMax);
	}

	public List<VilleTp6> findByDepartementAndNbHabitantsGreaterThan(DepartementTp6 departement, int minPopulation) {
		return villeRepository.findByDepartementAndNbHabitantsGreaterThan(departement, minPopulation);
	}
	
	public List<VilleTp6> findByDepartementAndNbHabitantsBetween(DepartementTp6 departement, int minPopulation, int maxPopulation){
		return villeRepository.findByDepartementAndNbHabitantsBetween(departement, minPopulation, maxPopulation);
	}
	
	public List<VilleTp6> TopNVillesByDepartementMaxPop(DepartementTp6 departement, int nombre) {
		return villeRepository.TopNVillesByDepartementMaxPop(departement, nombre);
	}

	public int insertVille(VilleTp6 ville) {

		int i = 0;
		VilleTp6 villeEnBase = findByNom(ville.getNom());

		if (villeEnBase == null) {
			departementService.insertDepartement(ville.getDepartement());
			ville.setDepartement(departementService.extractDepCode(ville.getDepartement().getCodeDep()));
			villeRepository.save(ville);
			i++;
		}
		return i;
	}

	public void modifierVille(VilleTp6 ville, int id) {
		departementService.modifierDepartement(ville.getDepartement(), ville.getDepartement().getId());
		VilleTp6 villeEnBase = findById(id);
		if (villeEnBase != null) {
			villeEnBase.setNom(ville.getNom());
			villeEnBase.setNbHabitants(ville.getNbHabitants());
			villeEnBase.setDepartement(ville.getDepartement());
			villeRepository.save(villeEnBase);
		}
	}

	public void supprimerVille(int idVille) {
		villeRepository.deleteById(idVille);
	}

}
