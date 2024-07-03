package fr.diginamic.spring_demo.controleurs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.spring_demo.entity.Ville;
import fr.diginamic.spring_demo.services.VilleInsertion;

@RestController
@RequestMapping("/villes")
public class VilleControleur {
	
	@Autowired
	public VilleInsertion v;

	@GetMapping
	public List<Ville> extraireVilles(){
		return List.of(v.insererVille("Montpellier", 350000),v.insererVille("Nimes", 260000));
	}
}
