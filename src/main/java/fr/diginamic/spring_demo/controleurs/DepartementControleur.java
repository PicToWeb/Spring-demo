package fr.diginamic.spring_demo.controleurs;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
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

import fr.diginamic.spring_demo.entity.DepartementTp6;
import fr.diginamic.spring_demo.services.DepartementService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/departements")
public class DepartementControleur {

	@Autowired
	public DepartementService departementService;
	
	
	/**
	 * @return Liste des départements
	 */
	@GetMapping
	public List<DepartementTp6> extraireVilles() {
		return departementService.extractDepartement();
	}
	/**
	 * departements/rechercheParId?id=1
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/rechercheParId")
	public ResponseEntity<DepartementTp6> extraireDepParId(@RequestParam int id) {
		return  ResponseEntity.ok(departementService.extractDepId(id));
	}
	
	/**
	 * departements/rechercheParNom/Occitanie
	 * 
	 * @param nom
	 * @return
	 */
	@GetMapping("/rechercheParNom/{nom}")
	public ResponseEntity<DepartementTp6> extraireDepParNom(@PathVariable String nom) {
		return  ResponseEntity.ok(departementService.extractDepNom(nom));
	}
	
	/**
	 * @param nvDep
	 * @param result
	 * @return
	 */
	@PostMapping
	public ResponseEntity<String> insererDep(@Valid @RequestBody DepartementTp6 nvDep,BindingResult result) {		
		if(result.hasErrors()){
			return ResponseEntity.badRequest().body("Les entrées ne sont pas exactes");
		}
		DepartementTp6 departement = departementService.extractDepId(nvDep.getId());
		if(departement == null) {
			departementService.insertDepartement(nvDep);
			return ResponseEntity.ok(departementService.extractDepartement().toString());
		}
		return ResponseEntity.badRequest().body("Une erreur s'est produit");
	}
	
	
	/**
	 * @param id
	 * @param editDepartement
	 * @param result
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<String> modifierDepartement(@Valid @PathVariable int id,@RequestBody DepartementTp6 editDepartement,BindingResult result){
		if(result.hasErrors()){
			return ResponseEntity.badRequest().body("Les données passées sont incorrectes");
		}
		DepartementTp6 departement =departementService.extractDepId(id);
		if(departement != null) {
			departementService.modifierDepartement(editDepartement, id);
			return ResponseEntity.ok(departementService.extractDepartement().toString());
		}
		return ResponseEntity.badRequest().body("Le département n'existe pas");
	}
	
	
	/**
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> supprimerDepartement(@PathVariable int id){
		DepartementTp6 ville = departementService.extractDepId(id);
		if(ville != null) {
			departementService.supprimerDepartement(id);
			return ResponseEntity.ok(departementService.extractDepartement().toString());
		}
		return ResponseEntity.badRequest().body("Le département n'existe pas !");
		
	}
	

}



