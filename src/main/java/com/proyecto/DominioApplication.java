package com.proyecto;

import com.proyecto.configuracion.Configuracion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableConfigurationProperties(Configuracion.class)
//@EnableMultipart
public class DominioApplication {

	public static void main(String[] args) {
		SpringApplication.run(DominioApplication.class, args);
	}


	@GetMapping("/hola")
	public String hola(@RequestParam(value = "nombre", defaultValue = "Mundo") String nombre) {
		return String.format("Hola %s!", nombre);
	}

}
