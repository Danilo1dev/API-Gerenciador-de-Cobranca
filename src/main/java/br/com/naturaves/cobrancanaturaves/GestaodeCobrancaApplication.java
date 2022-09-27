package br.com.naturaves.cobrancanaturaves;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class GestaodeCobrancaApplication {
	
	@GetMapping
	public String getHomeTeste() {
		return "Gestao de Cobranca - API";
	}
	public static void main(String[] args) {
		SpringApplication.run(GestaodeCobrancaApplication.class, args);
	}
}
