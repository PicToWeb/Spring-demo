package fr.diginamic.spring_demo.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.diginamic.spring_demo.entity.VilleTp6;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
public class VilleDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void persistVille(VilleTp6 ville) {
		em.persist(ville);
	}
	
	public List<VilleTp6> extractVilles(){
		TypedQuery<VilleTp6> query = em.createQuery("SELECT v FROM VilleTp6 v",VilleTp6.class);
		return query.getResultList();
	}
	
	@Transactional
	public void modifierVille(int idVille,VilleTp6 villeModifiee){
		VilleTp6 villeFromDb = em.find(VilleTp6.class, idVille);
		villeFromDb.setNom(villeModifiee.getNom());
		villeFromDb.setNbHabitants(villeModifiee.getNbHabitants());
	}
	
	@Transactional
	public void supprimerVille(int idVille) {
		VilleTp6 villeFromDb = em.find(VilleTp6.class, idVille);
		em.remove(villeFromDb);
	}
}
