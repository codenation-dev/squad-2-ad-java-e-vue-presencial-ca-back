package br.com.codenation.centralerrosapi;

import br.com.codenation.centralerrosapi.model.Log;
import br.com.codenation.centralerrosapi.model.LogDetails;
import br.com.codenation.centralerrosapi.model.Server;
import br.com.codenation.centralerrosapi.model.enums.Environment;
import br.com.codenation.centralerrosapi.model.enums.Level;
import br.com.codenation.centralerrosapi.repository.LogRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class CentralErrosApiApplication implements CommandLineRunner {

	private LogRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(CentralErrosApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<Server> servers = Arrays.asList(
				new Server().builder().host("127.0.0.1").environment(Environment.DEVELOPMENT).build(),
				new Server().builder().host("175.118.0.187").environment(Environment.TEST).build(),
				new Server().builder().host("192.168.0.1").environment(Environment.PRODUCTION).build()
		);

		List<LogDetails> details = Arrays.asList(
				new LogDetails().builder().description("Detalhe do log 1").date(LocalDateTime.now()).level(Level.ERROR).events(10).server(servers.get(0)).build(),
				new LogDetails().builder().description("Detalhe do log 2").date(LocalDateTime.now()).level(Level.DEBUG).events(100).server(servers.get(1)).build(),
				new LogDetails().builder().description("Detalhe do log 3").date(LocalDateTime.now()).level(Level.WARNING).events(1000).server(servers.get(2)).build()
		);

		List<Log> logs = Arrays.asList(
				new Log().builder().title("Título do log 1").archived(false).details(details.get(0)).build(),
				new Log().builder().title("Título do log 2").archived(false).details(details.get(1)).build(),
				new Log().builder().title("Título do log 3").archived(true).details(details.get(2)).build()
		);

		repository.saveAll(logs);
	}
}
