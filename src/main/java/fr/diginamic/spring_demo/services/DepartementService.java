package fr.diginamic.spring_demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.spring_demo.entity.DepartementTp6;
import fr.diginamic.spring_demo.repositories.DepartementRepository;
import jakarta.annotation.PostConstruct;

@Service
public class DepartementService {

	@Autowired
	private DepartementRepository departementRepository;
	
	private List<DepartementTp6> liste = new ArrayList<>();
	

	@PostConstruct
	public void initDonne() {
		this.liste = extractDepartement();
	}

	public List<DepartementTp6> extractDepartement(){
		Iterable<DepartementTp6> iteList = departementRepository.findAll();
		return IteratorUtils.toList(iteList.iterator());
	}
	
		
	public DepartementTp6 extractDepId(String idDep) {
		return liste.stream().filter(v->v.getId() != null && v.getId().equals(idDep)).findFirst().orElse(null);
	}
	
	public DepartementTp6 findById(String id) {
		return departementRepository.findById(id).orElse(null);
	}
	
	public DepartementTp6 extractDepNom(String nomDep) {
		return liste.stream().filter(v->v.getNom() !=null && v.getNom().equals(nomDep)).findFirst().orElse(null);
	}
	
	
	public void insertDepartement(DepartementTp6 departement) {
		DepartementTp6 depNom = extractDepId(departement.getId());
		
		if(depNom == null) {
			departementRepository.save(departement);
			liste.add(departement);
		}
	}
	
	
	
	
	
//	public void modifierDepartement(DepartementTp6 departement,String id) {
//		DepartementTp6 depNom = extractDepNom(departement.getNom());
//		if(depNom == null) {
//			departementDao.modifierDepartement(id, departement);
//			departementRepository.
//		}
//	}
//	
//	public void supprimerDepartement(String idDepartement) {
//		departementDao.supprimerDepartement(idDepartement);
//	}
	
	
	
}
