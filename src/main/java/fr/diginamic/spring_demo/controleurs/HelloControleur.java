package fr.diginamic.spring_demo.controleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.spring_demo.services.HelloService;

@RestController
@RequestMapping("/saluer")
public class HelloControleur {
	
	@Autowired
	public HelloService service;
	
	@GetMapping("/hello")
	public String direHello() {
		return "Hello";
	}
	
	@GetMapping("/bonjour")
	public String direBonjour() {
		return service.salutations();
	}

}
