package fr.diginamic.spring_demo.services;

import org.springframework.stereotype.Service;

import fr.diginamic.spring_demo.entity.Ville;

@Service
public class VilleInsertion {

	public Ville insererVille(String nomVille, int nbHab) {
		
		Ville ville = new Ville(nomVille,nbHab);
		
		return ville;
	}
}
