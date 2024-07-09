package fr.diginamic.spring_demo.controleurs;

import fr.diginamic.spring_demo.dto.VilleDTO;
import fr.diginamic.spring_demo.entity.DepartementTp6;
import fr.diginamic.spring_demo.entity.VilleTp6;
import fr.diginamic.spring_demo.repositories.VilleRepository;
import fr.diginamic.spring_demo.services.DepartementService;
import fr.diginamic.spring_demo.services.VilleService;
import fr.diginamic.spring_demo.utilitaire.AnomalieException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    /**
     * villeService
     */
    @Autowired
    public VilleService villeService;

    /**
     * departementService
     */
    @Autowired
    public DepartementService departementService;
    /**
     * villeRepository
     */
    @Autowired
    private VilleRepository villeRepository;

    // TP - 6

    /**
     * Méthode qui retourne la liste des villes
     *
     * @return ResponseEntity<String>
     */
    @GetMapping
    public ResponseEntity<String> extraireVilles() {
        return ResponseEntity.ok(villeService.extractVilles().toString());
    }

    /**
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/pagination")
    public Page<VilleDTO> extraireVillesPage(@RequestParam int page, @RequestParam int size) {
        return villeRepository.findAll(PageRequest.of(page, size)).map(villeService::convertirVilleDto);
    }

    /**
     * villes/rechercheParId?id=1
     *
     * @param id correspond à l'identifiant de la ville
     * @return ResponseEntity<VilleTp6>
     */
    @GetMapping("/rechercheParId")
    public ResponseEntity<VilleTp6> extraireVilleParId(@RequestParam int id) {
        return ResponseEntity.ok(villeService.findById(id));
    }

    /**
     * villes/rechercheParNom/Montpellier
     *
     * @param nom correspond au nom de la ville
     * @return ResponseEntity<VilleTp6>
     */
    @GetMapping("/rechercheParNom/{nom}")
    public ResponseEntity<VilleTp6> extraireVilleParNom(@PathVariable String nom) {
        return ResponseEntity.ok(villeService.findByNom(nom));
    }

    /**
     * villes/rechercheNomCommence/Par
     *
     * @param nom correspond au nom de la ville
     * @return ResponseEntity<String>
     */
    @GetMapping("/rechercheNomCommence/{nom}")
    public ResponseEntity<String> extraireVilleCommencePar(@PathVariable String nom) throws AnomalieException {
        List<VilleTp6> villeListe = villeService.findByNomStartingWith(nom);
        if (villeListe.isEmpty()) {
            throw new AnomalieException("Aucune ville dont le nom commence par " + nom + " n'a été trouvée");
        }
        return ResponseEntity.ok(villeListe.toString());
    }

    /**
     * villes/rechercheVillePopMin/150000
     *
     * @param nb correspond au nombre minimal d'habitant
     * @return ResponseEntity<String>
     */
    @GetMapping("/rechercheVillePopMin/{nb}")
    public ResponseEntity<String> extraireVillePopMin(@PathVariable int nb) throws AnomalieException {
        List<VilleTp6> villeListe = villeService.findByNbHabitantsGreaterThan(nb);
        if (!villeListe.isEmpty()) {
            throw new AnomalieException("Aucune ville n'a une population supérieur à " + nb);
        }
        return ResponseEntity.ok(villeListe.toString());
    }

    /**
     * villes/recherchePopEntre?min=1&max=2500
     *
     * @param min nombre minimal d'habitant
     * @param max nombre maximal d'habitant
     * @return ResponseEntity<String>
     */
    @GetMapping("/recherchePopEntre")
    public ResponseEntity<String> extraireVillePopMinAndMax(@RequestParam int min, @RequestParam int max) throws AnomalieException {
        List<VilleTp6> villeListe = villeService.findByNbHabitantsBetween(min, max);
        if (villeListe.isEmpty()) {
            throw new AnomalieException("Aucune ville n'a une population comprise entre " + min + " et " + max);
        }
        return ResponseEntity.ok(villeListe.toString());
    }

    /**
     * villes/recherchePopDepVille?idDep=1&min=15000
     *
     * @param idDep numéro de l'identifiant du département
     * @param min   nombre minimal d'habitant
     * @return ResponseEntity<String>
     */
    @GetMapping("/recherchePopDepVille")
    public ResponseEntity<String> extraireVillePopMinInDepartement(@RequestParam int idDep, @RequestParam int min) throws AnomalieException {
        DepartementTp6 departement = departementService.findById(idDep);
        List<VilleTp6> villeListe = villeService.findByDepartementAndNbHabitantsGreaterThan(departement, min);
        if(villeListe.isEmpty()) {
            throw new AnomalieException("Aucune ville n'a une population supérieure à " + min + " dans le département " + idDep);
        }
        return ResponseEntity.ok(villeListe.toString());
    }

    /**
     * villes/recherchePopDepVilleEntre?idDep=2&min=15000&max=20000
     *
     * @param idDep numéro d'identifiant du département
     * @param min   nombre minimal d'habitant
     * @param max   nombre maximal d'habitant
     * @return ResponseEntity<String>
     */
    @GetMapping("/recherchePopDepVilleEntre")
    public ResponseEntity<String> extraireVillePopMinInDepartement(@RequestParam int idDep, @RequestParam int min,
                                                                   @RequestParam int max) throws AnomalieException {
        DepartementTp6 departement = departementService.findById(idDep);
        List<VilleTp6> villeListe = villeService.findByDepartementAndNbHabitantsBetween(departement, min, max);
        if(villeListe.isEmpty()) {
            throw new AnomalieException("Aucune ville n'a une population comprise entre " + min + " et " + max + " dans le département " + idDep);
        }
        return ResponseEntity.ok(villeListe.toString());
    }

    /**
     * villes/rechercheDesNVilleDep?idDep=2&nombre=15000
     *
     * @param idDep  numéro d'identifiant du département
     * @param nombre nombre de villes à afficher
     * @return ResponseEntity<String>
     */
    @GetMapping("/rechercheDesNVilleDep")
    public ResponseEntity<String> extraireNVilleInDepartement(@RequestParam int idDep, @RequestParam int nombre) throws AnomalieException {
        DepartementTp6 departement = departementService.findById(idDep);
        List<VilleTp6> villeListe = villeService.TopNVillesByDepartementMaxPop(departement, nombre);
        if(villeListe.isEmpty()) {
            throw new AnomalieException("Aucune ville n'est trouvé avec une population minimale de " + nombre + " dans le département " + idDep);
        }
        return ResponseEntity.ok(villeListe.toString());
    }

    /**
     * @param nvVille Correspond aux données d'une nouvelle ville
     * @param result  permet de tester les validations
     * @return ResponseEntity<String>
     */
    @PostMapping
    public ResponseEntity<String> insererVille(@Valid @RequestBody VilleTp6 nvVille, BindingResult result) throws AnomalieException {
        if (result.hasErrors()) {
            throw new AnomalieException(result.getAllErrors().stream().map(e->e.getDefaultMessage()).collect(Collectors.joining("\n")));
        }
        villeService.insertVille(nvVille);
        return ResponseEntity.ok(villeService.extractVilles().toString());
    }

    /**
     * @param id        correspond à l'identifiant de la ville
     * @param editVille correspond aux données de la ville
     * @param result    vérfie les données entrées
     * @return ResponseEntity<String>
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> modifierVille(@Valid @PathVariable int id, @RequestBody VilleTp6 editVille, BindingResult result) throws AnomalieException {
        if (result.hasErrors()) {
            throw new AnomalieException(result.getAllErrors().stream().map(e->e.getDefaultMessage()).collect(Collectors.joining("\n")));
        }
        VilleTp6 ville = villeService.findById(id);
        if (ville != null) {
            villeService.modifierVille(editVille, id);
            return ResponseEntity.ok(villeService.extractVilles().toString());
        }
        return ResponseEntity.badRequest().body("La ville n'existe pas");
    }

    /**
     * @param id identifiant de la ville
     * @return ResponseEntity<String>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVille(@PathVariable int id) throws AnomalieException {
        VilleTp6 ville = villeService.findById(id);
        if (ville == null) {
            throw new AnomalieException("Ville n'existe pas");
        }
        villeService.supprimerVille(id);
        return ResponseEntity.ok(villeService.extractVilles().toString());
    }

//	private List<Ville> liste = List.of(new Ville("Montpellier", 350000), new Ville("Nimes", 260000));
//	private List<Ville> liste = new ArrayList<>();
//	
//
//
//
//	/**
//	 * villes/rechercheParNom?nom=Montpellier
//	 * 
//	 * @param nom
//	 * @return
//	 */
//	@GetMapping("/rechercheParNom")
//	public Ville extraireVille(@RequestParam String nom) {
//		System.out.println(nom);
//
//		return liste.stream().filter(v -> v.getNom().equals(nom)).findFirst().orElse(null);
//
//	}
//
//	/**
//	 * villes/Montpellier
//	 * 
//	 * @param nom
//	 * @return
//	 */
//	@GetMapping("/nom/{nom}")
//	public Ville extraireVilleChemin(@PathVariable String nom) {
//		System.out.println(nom);
//
//		return liste.stream().filter(v -> v.getNom().equals(nom)).findFirst().orElse(null);
//
//	}

// TP 4 

//	@PostMapping
//	public ResponseEntity<String> insererVille(@RequestBody Ville nvVille) {
//		if(liste.contains(nvVille)) {
//			return ResponseEntity.badRequest().body("La ville" + nvVille.getNom() + "existe déjà");
//		}
//		liste.add(nvVille);
//		return ResponseEntity.ok("Succes!");
//	}

// TP 6

//	@GetMapping
//	public List<Ville> extraireVilles() {
//		return liste;
//	}

//	/** 
//	 *  L'annotation @GetMapping indique à Spring qu'il doit extraire une ville par son id
//	 * @param id
//	 * @return ville
//	 */
//	@GetMapping("/{id}")
//	public Ville extraireVilleParId(@PathVariable int id) {
//		return liste.stream().filter(v -> v.getId() == id).findFirst().orElse(null);
//	}
//	
//	@PostMapping
//	public ResponseEntity<String> insererVille(@Valid @RequestBody Ville nvVille,BindingResult result) {
//				
//		if(result.hasErrors()){
//			return ResponseEntity.badRequest().body("Les données passées en param sont incorrectes");
//		}
//		
//		Ville ville = extraireVilleParId(nvVille.getId());
//		if(ville == null) {
//			liste.add(nvVille);
//		return ResponseEntity.ok("Succes!");
//		}
//		return ResponseEntity.badRequest().body("La ville existe déjà");
//	}
//	
//	@PutMapping("/{id}")
//	public ResponseEntity<String> modifierVille(@Valid @PathVariable int id,@RequestBody Ville editVille,BindingResult result){
//		
//		if(result.hasErrors()){
//			return ResponseEntity.badRequest().body("Les données passées en param sont incorrectes");
//		}
//		
//		Ville ville = extraireVilleParId(id);
//		if(ville != null) {
//			ville.setId(editVille.getId());
//			ville.setNom(editVille.getNom());
//			ville.setNbHabitants(editVille.getNbHabitants());
//			return ResponseEntity.ok("Succes!");
//		}
//		return ResponseEntity.badRequest().body("La ville n'existe pas");
//	}
//	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<String> deleteVille(@PathVariable int id){
//		Ville ville = extraireVilleParId(id);
//		if(ville != null) {
//			liste.remove(ville);
//			return ResponseEntity.ok("Succes!");
//		}
//		return ResponseEntity.badRequest().body("La ville n'existe pas !");
//		
//	}
}
