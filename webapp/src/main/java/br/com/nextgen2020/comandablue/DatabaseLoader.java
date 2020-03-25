package br.com.nextgen2020.comandablue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.nextgen2020.comandablue.model.entidade.Usuario;
import br.com.nextgen2020.comandablue.repository.UserRepository;

@Component // automatically picked up by @SpringBootApplication
// CommandLineRunner, runs after all the beans are created and registered.
public class DatabaseLoader implements CommandLineRunner {

    private final UserRepository repository;

    @Autowired
    public DatabaseLoader(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.repository.save(new Usuario("denadai.sicari@gmail.com", "Danilo de Nadai Sicari", "12345"));
    }
}
