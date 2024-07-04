package fr.diginamic.spring_demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.spring_demo.dao.VilleDao;
import fr.diginamic.spring_demo.dto.VilleDto;
import fr.diginamic.spring_demo.entity.VilleTp6;
import jakarta.annotation.PostConstruct;

@Service
public class VilleService {

	@Autowired
	private VilleDao villeDao;
	
	@Autowired
	private DepartementService departementService;
	
	private List<VilleTp6> liste = new ArrayList<>();
	

	@PostConstruct
	public void initDonne() {
		this.liste = extractVilles();
	}

	public List<VilleTp6> extractVilles(){
		
		return villeDao.extractVilles();
	}
	
	public VilleDto convertirVilleDto(VilleTp6 ville) {
		if(ville != null) {
			return new VilleDto(ville.getId(),ville.getNom(),ville.getDepartement().getNom());
		}
		return null;
	}
	
	public VilleTp6 extractVilleId(int idVille) {
		return villeDao.extractVille(idVille);
	}
	
	public VilleTp6 extractVilleNom(String nomVille) {
		return liste.stream().filter(v->v.getNom().equals(nomVille)).findFirst().orElse(null);
	}
	
	
	public void insertVille(VilleTp6 ville) {
		
		ville.setDepartement(departementService.extractDepId(1));
		villeDao.persistVille(ville);
		liste.add(ville);
	}
	
	public void modifierVille(VilleTp6 ville,int id) {
		villeDao.modifierVille(id, ville);
	}
	
	public void supprimerVille(int idVille) {
		villeDao.supprimerVille(idVille);
	}
	
	
	
}
