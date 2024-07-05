package fr.diginamic.spring_demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.spring_demo.dao.VilleDao;
import fr.diginamic.spring_demo.dto.VilleDTO;
import fr.diginamic.spring_demo.entity.VilleTp6;

@Service
public class VilleService {

	@Autowired
	private VilleDao villeDao;

	@Autowired
	private DepartementService departementService;

//
//	@PostConstruct
//	public void initDonne() {
//		this.liste = extractVilles();
//	}

	public List<VilleDTO> extractVilles() {
		List<VilleTp6> villes = villeDao.extractVilles();
		return villes.stream().map(v -> convertirVilleDto(v)).collect(Collectors.toList());
	}

	public VilleDTO convertirVilleDto(VilleTp6 ville) {
		if (ville != null) {
			return new VilleDTO(ville.getId(), ville.getNom(), ville.getDepartement().getNom());
		}
		return null;
	}

	public VilleTp6 extractVilleId(int idVille) {
		return villeDao.extractVilleId(idVille);
	}

	public VilleTp6 extractVilleNom(String nomVille) {
		return villeDao.extractVilleNom(nomVille);
	}

	public String insertVille(VilleTp6 ville) {
	
		VilleTp6 villeTrouve = extractVilleNom(ville.getNom());
		
		if (villeTrouve == null) {
			departementService.insertDepartement(ville.getDepartement());
			villeDao.persistVille(ville);
			return extractVilles().toString();
		}
		
		return "La ville " + villeTrouve.getNom() + " existe déjà \n " + extractVilles().toString();
	}

	public void modifierVille(VilleTp6 ville, int id) {
		departementService.modifierDepartement(ville.getDepartement(), ville.getDepartement().getId());
		villeDao.modifierVille(id, ville);
	}

	public void supprimerVille(int idVille) {
		villeDao.supprimerVille(idVille);
	}

}
