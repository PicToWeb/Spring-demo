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
	
//	@Autowired
//	private VilleRepository villeRepo;
	
//	
//	@PersistenceContext
//	private EntityManager em;
//	
//	@Transactional
//	public void persistVille(VilleTp6 ville) {
//		em.persist(ville);
//	}
//	
//	public List<VilleTp6> extractVilles(){
//		TypedQuery<VilleTp6> query = em.createQuery("SELECT v FROM VilleTp6 v JOIN FETCH v.departement",VilleTp6.class);
//		return query.getResultList();
//	}
//	
//	public VilleTp6 extractVilleId(int idVille) {
//		TypedQuery<VilleTp6> query = em.createQuery("SELECT v FROM VilleTp6 v JOIN FETCH v.departement WHERE v.id=:idVille",VilleTp6.class);
//		 query.setParameter("idVille", idVille);
//		 List<VilleTp6> villes = query.getResultList();
//		 if(villes.size()>0) {
//			 return query.getSingleResult();
//		 }
//		 return null;
//	}
//	
//	public VilleTp6 extractVilleNom(String nomVille) {
//		TypedQuery<VilleTp6> query = em.createQuery("SELECT v FROM VilleTp6 v JOIN FETCH v.departement WHERE v.nom=:nomVille",VilleTp6.class);
//		 query.setParameter("nomVille", nomVille);
//		 List<VilleTp6> villes = query.getResultList();
//		 if(villes.size()>0) {
//			 return query.getSingleResult();
//		 }
//		 return null;
//	}
//	
//	@Transactional
//	public void modifierVille(int idVille,VilleTp6 villeModifiee){
//		VilleTp6 villeFromDb = em.find(VilleTp6.class, idVille);
//		villeFromDb.setNom(villeModifiee.getNom());
//		villeFromDb.setNbHabitants(villeModifiee.getNbHabitants());
//		villeFromDb.setDepartement(villeModifiee.getDepartement());
//	}
//	
//	@Transactional
//	public void supprimerVille(int idVille) {
//		VilleTp6 villeFromDb = em.find(VilleTp6.class, idVille);
//		em.remove(villeFromDb);
//	}
}
