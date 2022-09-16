package com.Ciclo3.ProyectoArray;

import com.Ciclo3.ProyectoArray.models.Empresa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
@RestController

 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ProyectoArrayApplication {
//HASTA ESTE PUNTO NO SE HA IMPLEMETADO SEGURIDAD
	/*

	@GetMapping("/hello")
	public String hello(){
		return "Hola equipo, Saldremos vivos de esto!!!!";
	}

	@GetMapping("/test")
	public String test(){
		Empresa empresa1 = new Empresa("Tekman SAS", "calle de la rumba","3112633288","800900-4");
		empresa1.setNombre("AREPAS LA SABROSA");
		return empresa1.getNombre();
	}

*/
	public static void main(String[] args) {
		SpringApplication.run(ProyectoArrayApplication.class, args);
	}

}
