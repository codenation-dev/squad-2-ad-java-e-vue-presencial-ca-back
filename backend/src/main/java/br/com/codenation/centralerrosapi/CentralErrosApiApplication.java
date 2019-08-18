package br.com.codenation.centralerrosapi;

import br.com.codenation.centralerrosapi.models.Error;
import br.com.codenation.centralerrosapi.models.ErrorEnvironment;
import br.com.codenation.centralerrosapi.models.ErrorLevel;
import br.com.codenation.centralerrosapi.repositories.ErrorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.Arrays;

@EnableJpaAuditing
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
				.environment(ErrorEnvironment.DEVELOPMENT)
				.level(ErrorLevel.DEBUG)
				.events(100)
				.archived(false)
				.build();

		Error e2 = new Error()
				.builder()
				.title("Título do erro 2")
				.detail("Detalhe do erro 2")
				.environment(ErrorEnvironment.TEST)
				.level(ErrorLevel.ERROR)
				.events(100)
				.archived(false)
				.build();

		Error e3 = new Error()
				.builder()
				.title("Título do erro 3")
				.detail("Detalhe do erro 3")
				.environment(ErrorEnvironment.PRODUCTION)
				.level(ErrorLevel.WARNING)
				.events(100)
				.archived(false)
				.build();

		repository.saveAll(Arrays.asList(e1, e2, e3));
	}
}
