package fr.diginamic.spring_demo.controleurs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.spring_demo.entity.Ville;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

//	@Autowired
//	public VilleInsertion v;

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
	@GetMapping("/nom/{nom}")
	public Ville extraireVilleChemin(@PathVariable String nom) {
		System.out.println(nom);

		return liste.stream().filter(v -> v.getNom().equals(nom)).findFirst().orElse(null);

	}
	
	
// TP 4 
//	@PostMapping
//	public ResponseEntity<String> insererVille(@RequestBody Ville nvVille) {
//		if(liste.contains(nvVille)) {
//			return ResponseEntity.badRequest().body("La ville" + nvVille.getNom() + "existe déjà");
//		}
//		liste.add(nvVille);
//		return ResponseEntity.ok("Succes!");
//	}
	
	
	/** 
	 *  L'annotation @GetMapping indique à Spring qu'il doit extraire une ville par son id
	 * @param id
	 * @return ville
	 */
	@GetMapping("/{id}")
	public Ville extraireVilleParId(@PathVariable int id) {
		return liste.stream().filter(v -> v.getId() == id).findFirst().orElse(null);
	}
	
	@PostMapping
	public ResponseEntity<String> insererVille(@Valid @RequestBody Ville nvVille,BindingResult result) {
		
	//	Validator.verif(result);
		
		if(result.hasErrors()){
			return ResponseEntity.badRequest().body("Les données passées en param sont incorrectes");
		}
		
		Ville ville = extraireVilleParId(nvVille.getId());
		if(ville == null) {
			liste.add(nvVille);
		return ResponseEntity.ok("Succes!");
		}
		return ResponseEntity.badRequest().body("La ville existe déjà");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> modifierVille(@Valid @PathVariable int id,@RequestBody Ville editVille,BindingResult result){
		
		if(result.hasErrors()){
			return ResponseEntity.badRequest().body("Les données passées en param sont incorrectes");
		}
		
		Ville ville = extraireVilleParId(id);
		if(ville != null) {
			ville.setId(editVille.getId());
			ville.setNom(editVille.getNom());
			ville.setNbHabitants(editVille.getNbHabitants());
			return ResponseEntity.ok("Succes!");
		}
		return ResponseEntity.badRequest().body("La ville n'existe pas");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteVille(@PathVariable int id){
		Ville ville = extraireVilleParId(id);
		if(ville != null) {
			liste.remove(ville);
			return ResponseEntity.ok("Succes!");
		}
		return ResponseEntity.badRequest().body("La ville n'existe pas !");
		
	}
}


