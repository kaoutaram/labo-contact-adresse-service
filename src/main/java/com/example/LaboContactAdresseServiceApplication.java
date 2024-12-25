package com.example;


import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entities.Adresse;
import com.example.services.AdresseService;






@SpringBootApplication
public class LaboContactAdresseServiceApplication implements CommandLineRunner {





	public static void main(String[] args) {
		SpringApplication.run(LaboContactAdresseServiceApplication.class, args);
		
	}
	@Override
	public void run(String... args) throws Exception {
		

}

}
