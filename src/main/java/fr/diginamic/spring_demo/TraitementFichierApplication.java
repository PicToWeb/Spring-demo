package fr.diginamic.spring_demo;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.diginamic.spring_demo.entity.VilleTp6;
import fr.diginamic.spring_demo.services.VilleService;
import fr.diginamic.spring_demo.utilitaire.PopComparateur;
import fr.diginamic.spring_demo.utilitaire.RecensementUtils;

@SpringBootApplication
public class TraitementFichierApplication implements CommandLineRunner{

	@Autowired
	private VilleService villeService;
	
	public static void main(String[] args) {
		
		SpringApplication application = new SpringApplication(TraitementFichierApplication.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);
		
	}
	
	@Override
	public void run(String... args) throws Exception{
		String filePath = ClassLoader.getSystemClassLoader().getResource("recensement.csv").getFile();
		List<VilleTp6> ville = RecensementUtils.lire(filePath);
		
		Collections.sort(ville, new PopComparateur(false));
		ville.stream().limit(10).forEach(v-> villeService.insertVille(v));

	}

}
