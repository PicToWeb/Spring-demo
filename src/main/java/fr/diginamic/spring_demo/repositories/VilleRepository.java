package fr.diginamic.spring_demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.spring_demo.entity.VilleTp6;

@Repository
public interface VilleRepository extends CrudRepository<VilleTp6,Integer> {

	VilleTp6 findByNom(String nomVille);
	
	 List<VilleTp6> findByNomStartingWith(String prefixe);
	
	 List<VilleTp6> findByNbHabitantsGreaterThan(int popMin);
	
	

}
