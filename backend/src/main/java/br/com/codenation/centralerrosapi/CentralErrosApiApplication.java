package br.com.codenation.centralerrosapi;

import br.com.codenation.centralerrosapi.model.Log;
import br.com.codenation.centralerrosapi.model.LogDetail;
import br.com.codenation.centralerrosapi.model.LogServer;
import br.com.codenation.centralerrosapi.model.enums.Environment;
import br.com.codenation.centralerrosapi.model.enums.Level;
import br.com.codenation.centralerrosapi.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class CentralErrosApiApplication implements CommandLineRunner {

	@Autowired
	private LogRepository logRepository;

	public static void main(String[] args) {
		SpringApplication.run(CentralErrosApiApplication.class, args);
	}

	@Override
	public void run(String... args) {

		LocalDateTime timestamp = LocalDateTime.now();

		LogServer server = new LogServer().builder()
				.ip("127.0.0.1")
				.hostname("java-client-test")
				.environment(Environment.DEVELOPMENT)
				.application("central-api-rest")
				.build();

		LogDetail detail = new LogDetail().builder()
				.timestamp(timestamp)
				.level(Level.ERROR)
				.content("Detalhe do log")
				.build();

		Log log = new Log().builder()
				.title("Título do log 1")
				.server(server)
				.detail(detail)
				.archived(false)
				.build();

		logRepository.save(log);

	}
}
