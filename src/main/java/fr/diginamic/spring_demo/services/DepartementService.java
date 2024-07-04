package fr.diginamic.spring_demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.spring_demo.dao.DepartementDao;
import fr.diginamic.spring_demo.entity.DepartementTp6;
import jakarta.annotation.PostConstruct;

@Service
public class DepartementService {

	@Autowired
	private DepartementDao departementDao;
	
	private List<DepartementTp6> liste = new ArrayList<>();
	

	@PostConstruct
	public void initDonne() {
		this.liste = extractDepartement();
	}

	public List<DepartementTp6> extractDepartement(){
		return departementDao.extractDepartement();
	}
	
	
	public DepartementTp6 extractDepId(int idDep) {
		return liste.stream().filter(v->v.getId() == idDep).findFirst().orElse(null);
	}
	
	public DepartementTp6 extractDepNom(String nomDep) {
		return liste.stream().filter(v->v.getNom().equals(nomDep)).findFirst().orElse(null);
	}
	
	
	public void insertDepartement(DepartementTp6 departement) {
		departementDao.persistVille(departement);
		liste.add(departement);
	}
	
	public void modifierDepartement(DepartementTp6 departement,int id) {
		departementDao.modifierDepartement(id, departement);
	}
	
	public void supprimerDepartement(int idDepartement) {
		departementDao.supprimerDepartement(idDepartement);
	}
	
	
	
}
