package fr.diginamic.spring_demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.spring_demo.entity.DepartementTp6;
import fr.diginamic.spring_demo.entity.VilleTp6;

@Repository
public interface VilleRepository extends CrudRepository<VilleTp6,Integer> {

	VilleTp6 findByNom(String nomVille);
	
	 List<VilleTp6> findByNomStartingWith(String prefixe);
	
	 List<VilleTp6> findByNbHabitantsGreaterThan(int popMin);
	 
	 List<VilleTp6> findByNbHabitantsBetween(int popMin, int popMax);
	
	 List<VilleTp6> findByDepartementAndNbHabitantsGreaterThan(DepartementTp6 departement, int minPopulation);
	 
	 List<VilleTp6> findByDepartementAndNbHabitantsBetween(DepartementTp6 departement, int minPopulation, int maxPopulation);
	 
	 @Query("SELECT v FROM VilleTp6 v WHERE v.departement = ?1 ORDER BY v.nbHabitants DESC")
	 List<VilleTp6> TopNVillesByDepartementMaxPop(DepartementTp6 departement, int n);
	

}
