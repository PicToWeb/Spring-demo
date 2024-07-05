package fr.diginamic.spring_demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.spring_demo.entity.DepartementTp6;
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

	public void insertVille(VilleTp6 ville) {
	
		VilleTp6 villeTrouve = findByNom(ville.getNom());

		if (villeTrouve == null) {
			departementService.insertDepartement(ville.getDepartement());
			ville.setDepartement(departementService.extractDepId(ville.getDepartement().getId()));
			villeRepository.save(ville);		
		}
		
	}

//	public void modifierVille(VilleTp6 ville, int id) {
//		departementService.modifierDepartement(ville.getDepartement(), ville.getDepartement().getId());
//		villeDao.modifierVille(id, ville);
//	}
//
//	public void supprimerVille(int idVille) {
//		villeDao.supprimerVille(idVille);
//	}

}
