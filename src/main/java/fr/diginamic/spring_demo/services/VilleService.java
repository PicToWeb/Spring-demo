package fr.diginamic.spring_demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.spring_demo.entity.VilleTp6;
import fr.diginamic.spring_demo.repositories.VilleRepository;

@Service
public class VilleService {

	@Autowired
	private VilleRepository villeRepository;

	@Autowired
	private DepartementService departementService;

//
//	@PostConstruct
//	public void initDonne() {
//		this.liste = extractVilles();
//	}

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

	public VilleTp6 findById(int idVille) {
		return villeRepository.findById(idVille).orElse(null);
	}

	public VilleTp6 findByNom(String nomVille) {
		return villeRepository.findByNom(nomVille);
	}
	
	public List<VilleTp6> findByNomStartingWith(String nomVille){
		return villeRepository.findByNomStartingWith(nomVille);
	}
	
	public List<VilleTp6> findByNbHabitantsGreaterThan(int popMin){
		return villeRepository.findByNbHabitantsGreaterThan(popMin);
	}
		

	public int insertVille(VilleTp6 ville) {
	
		int i=0;
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
		departementService.modifierDepartement(ville.getDepartement(),ville.getDepartement().getId());
		VilleTp6 villeEnBase = findById(id);
		if(villeEnBase !=null) {
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
