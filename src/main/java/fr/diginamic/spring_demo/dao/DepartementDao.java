package fr.diginamic.spring_demo.dao;

import java.util.List;



import org.springframework.stereotype.Service;

import fr.diginamic.spring_demo.entity.DepartementTp6;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
public class DepartementDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void persistDepartement(DepartementTp6 departement) {
		em.persist(departement);
	}
	
	public List<DepartementTp6> extractDepartement(){
		TypedQuery<DepartementTp6> query = em.createQuery("SELECT d FROM DepartementTp6 d",DepartementTp6.class);
		return query.getResultList();
	}
	
	@Transactional
	public void modifierDepartement(int idDep,DepartementTp6 depModifiee){
		DepartementTp6 depFromDb = em.find(DepartementTp6.class, idDep);
		depFromDb.setNom(depModifiee.getNom());
	}
	
	@Transactional
	public void supprimerDepartement(int idDep) {
		DepartementTp6 depFromDb = em.find(DepartementTp6.class, idDep);
		em.remove(depFromDb);
	}
}
