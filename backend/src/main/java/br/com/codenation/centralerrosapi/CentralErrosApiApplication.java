package br.com.codenation.centralerrosapi;

import br.com.codenation.centralerrosapi.model.Log;
import br.com.codenation.centralerrosapi.model.LogDetail;
import br.com.codenation.centralerrosapi.model.Server;
import br.com.codenation.centralerrosapi.model.enums.Environment;
import br.com.codenation.centralerrosapi.model.enums.Level;
import br.com.codenation.centralerrosapi.repository.LogDetailRepository;
import br.com.codenation.centralerrosapi.repository.LogRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class CentralErrosApiApplication implements CommandLineRunner {

	@Autowired
	private LogRepository logRepository;

	@Autowired
	private LogDetailRepository logDetailRepository;

	public static void main(String[] args) {
		SpringApplication.run(CentralErrosApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		LocalDateTime timestamp = LocalDateTime.now();
		Server server = new Server().builder().ip("127.0.0.1").hostname("java-client-test").build();

		Log log = new Log().builder()
				.title("Título do log 1")
				.environment(Environment.DEVELOPMENT)
				.application("central-api-rest")
				.server(server)
				.details(new ArrayList<LogDetail>())
				.archived(false).build();

		List<LogDetail> lines = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			LogDetail line = new LogDetail().builder()
					.line("Detalhe do log " + i)
					.timestamp(timestamp)
					.level(Level.ERROR)
					.log(log)
					.build();
			lines.add(line);
			log.getDetails().add(line);
		}

		logRepository.save(log);
		logDetailRepository.saveAll(lines);

	}
}
