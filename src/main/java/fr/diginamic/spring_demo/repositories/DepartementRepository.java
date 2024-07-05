package fr.diginamic.spring_demo.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.spring_demo.entity.DepartementTp6;

@Repository
public interface DepartementRepository extends CrudRepository<DepartementTp6,String> {
	
	@Override
	Optional<DepartementTp6> findById(String id);

}
