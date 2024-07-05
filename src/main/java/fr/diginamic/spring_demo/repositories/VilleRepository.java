package fr.diginamic.spring_demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.spring_demo.entity.VilleTp6;

@Repository
public interface VilleRepository extends CrudRepository<VilleTp6,Integer> {

	VilleTp6 findByNom(String nomVille);
	
	

}
