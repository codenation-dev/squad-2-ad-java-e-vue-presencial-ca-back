package br.com.codenation.centralerrosapi;

import br.com.codenation.centralerrosapi.model.Log;
import br.com.codenation.centralerrosapi.model.LogDetails;
import br.com.codenation.centralerrosapi.model.enums.Environment;
import br.com.codenation.centralerrosapi.model.enums.Level;
import br.com.codenation.centralerrosapi.repository.LogRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@AllArgsConstructor
public class CentralErrosApiApplication implements CommandLineRunner {

	private LogRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(CentralErrosApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		LogDetails details1 = new LogDetails()
				.builder()
				.detail("Detalhe do log 1")
				.host("127.0.0.1")
				.environment(Environment.DEVELOPMENT)
				.level(Level.DEBUG)
				.events(100)
				.build();

		Log log1 = new Log().builder().title("Título do log 1").archived(false).details(details1).build();

		LogDetails details2 = new LogDetails()
				.builder()
				.detail("Detalhe do log 2")
				.host("127.0.0.1")
				.environment(Environment.TEST)
				.level(Level.ERROR)
				.events(100)
				.build();

		Log log2 = new Log().builder().title("Título do erro 2").archived(true).details(details2).build();

		LogDetails details3 = new LogDetails()
				.builder()
				.detail("Detalhe do log 3")
				.host("127.0.0.1")
				.environment(Environment.PRODUCTION)
				.level(Level.WARNING)
				.events(100)
				.build();

		Log log3 = new Log().builder().title("Título do erro 3").archived(false).details(details3).build();

		repository.saveAll(Arrays.asList(log1, log2, log3));
	}
}
