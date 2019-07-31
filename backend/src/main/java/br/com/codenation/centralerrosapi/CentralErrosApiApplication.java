package br.com.codenation.centralerrosapi;

import br.com.codenation.centralerrosapi.models.Error;
import br.com.codenation.centralerrosapi.repositories.ErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class CentralErrosApiApplication implements CommandLineRunner {

	@Autowired
	private ErrorRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(CentralErrosApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Error e1 = new Error();
		e1.setTitle("Título do erro 1");
		e1.setDetail("Detalhe do erro 1");
		e1.setLevel(1);
		e1.setEvents(100);
		e1.setCreatedAt(LocalDateTime.now());
		e1.setUpdatedAt(null);
		e1.setArquived(false);

		repository.save(e1);

	}
}
