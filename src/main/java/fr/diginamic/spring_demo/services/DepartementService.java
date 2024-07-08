package fr.diginamic.spring_demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.spring_demo.dto.DepartementDTO;
import fr.diginamic.spring_demo.dto.VilleDTO;
import fr.diginamic.spring_demo.entity.DepartementTp6;
import fr.diginamic.spring_demo.entity.VilleTp6;
import fr.diginamic.spring_demo.repositories.DepartementRepository;
import jakarta.annotation.PostConstruct;

/**
 * Classe de service utilisée pour les départements. Elle est appelée par le
 * controleur et utilise le repository pour retourner des valeurs.
 * 
 */
@Service
public class DepartementService {

	/** departementRepository */
	@Autowired
	private DepartementRepository departementRepository;

	/** liste */
	private List<DepartementTp6> liste = new ArrayList<>();

	/**
	 * Constructeur utilisé pour charger la liste des départements présent en base
	 * de données
	 * 
	 */
	@PostConstruct
	public void initDonne() {
		this.liste = extractDepartement();
	}

	/**
	 * Methode utilisée pour récuper tous les départements présent en base de
	 * données
	 * 
	 * @return List<DepartementTp6>
	 */
	public List<DepartementTp6> extractDepartement() {
		Iterable<DepartementTp6> iteList = departementRepository.findAll();
		return IteratorUtils.toList(iteList.iterator());
	}

	/**
	 * Méhthode qui récupère le département contenant le code de département reçu
	 * 
	 * @param codeDep
	 * @return DepartementTp6
	 */
	public DepartementTp6 extractDepCode(String codeDep) {
		return liste.stream().filter(v -> v.getCodeDep() != null && v.getCodeDep().equals(codeDep)).findFirst()
				.orElse(null);
	}

	/**
	 * Méthode qui récupère le département en base de donnée via l'id reçu
	 * 
	 * @param id
	 * @return DepartementTp6
	 */
	public DepartementTp6 findById(int id) {
		return departementRepository.findById(id).orElse(null);
	}

	/**
	 * Méthode qui récupère le département de la liste via le nom du département
	 * reçu
	 * 
	 * @param nomDep
	 * @return
	 */
	public DepartementTp6 extractDepNom(String nomDep) {
		return liste.stream().filter(v -> v.getNom() != null && v.getNom().equals(nomDep)).findFirst().orElse(null);
	}

	

	/**
	 * Méthode qui vérifie la présence du département en base de donnée et qui
	 * l'ajoute s'il n'existe pas
	 * 
	 * @param departement
	 */
	public void insertDepartement(DepartementTp6 departement) {
		DepartementTp6 departementEnBase = extractDepCode(departement.getCodeDep());

		if (departementEnBase == null) {
			departementRepository.save(departement);
			liste.add(departement);
		}
	}

	/**
	 * Methode qui converti un département en DTO.
	 * 
	 * @param departement
	 * @return DepartementDto
	 */
	public DepartementDTO convertirDepartementDto(DepartementTp6 departement) {
		if (departement != null) {
			return new DepartementDTO(departement.getCodeDep(), departement.getNom(), departement.getVilles().stream()
					.map(v ->new VilleDTO(v.getId(), v.getNom(),v.getNbHabitants(), v.getDepartement().getNom())).toList(),compterPopulation(departement.getVilles()));
		}
		return null;
	}

	/** 
	 * Méthode qui compte le nombre d'habitant d'une liste de ville
	 * @param listeVille
	 * @return int
	 */
	public int compterPopulation(Set<VilleTp6> listeVille) {
		int tot = 0;
		for (VilleTp6 v : listeVille) {
			tot += v.getNbHabitants();
		}
		return tot;
	}
	
	/**
	 * Méthode qui modifie un département en base de donnée
	 * 
	 * @param departement
	 * @param id
	 */
	public void modifierDepartement(DepartementTp6 departement, int id) {
		DepartementTp6 departementEnBase = findById(id);
		if (departementEnBase != null) {
			departementEnBase.setCodeDep(departement.getCodeDep());
			departementEnBase.setNom(departement.getNom());
			departementRepository.save(departementEnBase);
		}
	}

	/**
	 * Méthode qui supprime un département en base de donnée
	 * 
	 * @param idDepartement
	 */
	public void supprimerDepartement(int idDepartement) {
		departementRepository.deleteById(idDepartement);
	}

}
