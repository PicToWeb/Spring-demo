package fr.diginamic.spring_demo.controleurs;

import java.util.List;

import java.util.stream.Collectors;

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

import fr.diginamic.spring_demo.dto.DepartementDTO;
import fr.diginamic.spring_demo.entity.DepartementTp6;
import fr.diginamic.spring_demo.services.DepartementService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/departements")
public class DepartementControleur {

	@Autowired
	public DepartementService departementService;

	/**
	 * Méthode qui récupère et affiche tous les départements présent en base de
	 * donnée
	 *
	 * @return Liste des départements
	 */
	@GetMapping
	public List<DepartementDTO> extraireVilles() {
		List<DepartementTp6> departList = departementService.extractDepartement();


		return departList.stream().map(dep -> departementService.convertirDepartementDto(dep))
				.collect(Collectors.toList());

	}

	/**
	 * Méthode qui recherche un département via son id
	 *
	 * departements/rechercheParId?id=1
	 *
	 * @param id
	 * @return ResponseEntity
	 */
	@GetMapping("/rechercheParId")
	public ResponseEntity<DepartementDTO> extraireDepParId(@RequestParam int id) {
		DepartementTp6 dep = departementService.findById(id);
		DepartementDTO depDTO = departementService.convertirDepartementDto(dep);

		return ResponseEntity.ok(depDTO);
	}

	/**
	 * Méthode qui recherche un département via son nom
	 * departements/rechercheParNom/Occitanie
	 *
	 * @param nom
	 * @return ResponseEntity
	 */
	@GetMapping("/rechercheParNom/{nom}")
	public ResponseEntity<DepartementDTO> extraireDepParNom(@PathVariable String nom) {
		DepartementTp6 dep = departementService.extractDepNom(nom);
		return ResponseEntity.ok(departementService.convertirDepartementDto(dep));
	}

	/**
	 * Méthode POST qui permet d'insérer un département en base de donnée
	 *
	 * @param nvDep
	 * @param result
	 * @return ResponseEntity
	 */
	@PostMapping
	public ResponseEntity<String> insererDep(@Valid @RequestBody DepartementTp6 nvDep, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body("Les entrées ne sont pas exactes");
		}
		DepartementTp6 departement = departementService.extractDepCode(nvDep.getCodeDep());
		if (departement == null) {
			departementService.insertDepartement(nvDep);
			return ResponseEntity.ok(departementService.extractDepartement().toString());
		}
		return ResponseEntity.badRequest().body("Une erreur s'est produit");
	}

	/**
	 * Méthode PUT qui permet de modifier un département en base de donnée
	 *
	 * @param id
	 * @param editDepartement
	 * @param result
	 * @return ResponseEntity
	 */
	@PutMapping("/{id}")
	public ResponseEntity<String> modifierDepartement(@Valid @PathVariable int id,
			@RequestBody DepartementTp6 editDepartement, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body("Les données passées sont incorrectes");
		}
		DepartementTp6 departement = departementService.findById(id);
		if (departement != null) {
			departementService.modifierDepartement(editDepartement, id);
			return ResponseEntity.ok(departementService.extractDepartement().toString());
		}
		return ResponseEntity.badRequest().body("Le département n'existe pas");
	}

	/**
	 * Méthode DELETE qui permet de supprimer un département en base de donnée
	 *
	 * @param id
	 * @return ResponseEntity
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> supprimerDepartement(@PathVariable int id) {
		DepartementTp6 ville = departementService.findById(id);
		if (ville != null) {
			departementService.supprimerDepartement(id);
			return ResponseEntity.ok(departementService.extractDepartement().toString());
		}
		return ResponseEntity.badRequest().body("Le département n'existe pas !");

	}

}
