package fr.diginamic.spring_demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.diginamic.spring_demo.entity.VilleTp6;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
public class VilleService {

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
	
	public VilleTp6 extractVilleId(int idVille) {
		TypedQuery<VilleTp6> query = em.createQuery("SELECT v FROM VilleTp6 v WHERE v.id=:idVille",VilleTp6.class);
		query.setParameter("idVille", idVille);
		return query.getSingleResult();
	}
	
	public VilleTp6 extractVilleNom(String nomVille) {
		TypedQuery<VilleTp6> query = em.createQuery("SELECT v FROM VilleTp6 v WHERE v.nom=:nomVille",VilleTp6.class);
		query.setParameter("nomVille", nomVille);
		return query.getSingleResult();
	}
	
	
	public List<VilleTp6> insertVille(VilleTp6 ville) {
		persistVille(ville);
		return extractVilles();
	}
	
	
	
}
