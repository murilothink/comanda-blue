package br.com.nextgen2020.comandablue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
@SpringBootApplication
public class ComandablueApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComandablueApplication.class, args);
	}

}
