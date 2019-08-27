package br.com.codenation.centralerrosapi;

import br.com.codenation.centralerrosapi.model.Error;
import br.com.codenation.centralerrosapi.model.enums.Environment;
import br.com.codenation.centralerrosapi.model.enums.Level;
import br.com.codenation.centralerrosapi.repository.ErrorRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@AllArgsConstructor
public class CentralErrosApiApplication implements CommandLineRunner {

	private ErrorRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(CentralErrosApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Error e1 = new Error()
				.builder()
				.title("Título do erro 1")
				.detail("Detalhe do erro 1")
				.host("127.0.0.1")
				.environment(Environment.DEVELOPMENT)
				.level(Level.DEBUG)
				.events(100)
				.archived(false)
				.build();

		Error e2 = new Error()
				.builder()
				.title("Título do erro 2")
				.detail("Detalhe do erro 2")
				.host("127.0.0.1")
				.environment(Environment.TEST)
				.level(Level.ERROR)
				.events(100)
				.archived(true)
				.build();

		Error e3 = new Error()
				.builder()
				.title("Título do erro 3")
				.detail("Detalhe do erro 3")
				.host("127.0.0.1")
				.environment(Environment.PRODUCTION)
				.level(Level.WARNING)
				.events(100)
				.archived(false)
				.build();

		repository.saveAll(Arrays.asList(e1, e2, e3));
	}
}
