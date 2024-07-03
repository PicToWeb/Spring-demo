package fr.diginamic.spring_demo.controleurs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.spring_demo.entity.Ville;
import fr.diginamic.spring_demo.services.VilleInsertion;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

	@Autowired
	public VilleInsertion v;

//	private List<Ville> liste = List.of(new Ville("Montpellier", 350000), new Ville("Nimes", 260000));
	private List<Ville> liste = new ArrayList<>();

	@GetMapping
	public List<Ville> extraireVilles() {
		return liste;
	}

	/**
	 * villes/rechercheParNom?nom=Montpellier
	 * 
	 * @param nom
	 * @return
	 */
	@GetMapping("/rechercheParNom")
	public Ville extraireVille(@RequestParam String nom) {
		System.out.println(nom);

		return liste.stream().filter(v -> v.getNom().equals(nom)).findFirst().orElse(null);

	}

	/**
	 * villes/Montpellier
	 * 
	 * @param nom
	 * @return
	 */
	@GetMapping("/{nom}")
	public Ville extraireVilleChemin(@PathVariable String nom) {
		System.out.println(nom);

		return liste.stream().filter(v -> v.getNom().equals(nom)).findFirst().orElse(null);

	}

	@PostMapping
	public ResponseEntity<String> insererVille(@RequestBody Ville nvVille) {
		
		Ville ville = liste.stream().filter(v -> v.getNom().equals(nvVille.getNom())).findFirst().orElse(null);
		if(ville == null) {
			liste.add(nvVille);
		return ResponseEntity.ok("Succes!");
		}
		return ResponseEntity.badRequest().body("La ville existe déjà");
	}
}
