package fr.diginamic.spring_demo.controleurs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saluer")
public class HelloControleur {
	
	@GetMapping("/hello")
	public String direHello() {
		return "Hello";
	}
	
	@GetMapping("/bonjour")
	public String direBonjour() {
		return "Bonjour";
	}

}
