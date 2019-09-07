package br.com.codenation.centralerrosapi;

import br.com.codenation.centralerrosapi.model.Log;
import br.com.codenation.centralerrosapi.model.LogApplication;
import br.com.codenation.centralerrosapi.model.LogDetail;
import br.com.codenation.centralerrosapi.model.enums.LogEnvironment;
import br.com.codenation.centralerrosapi.model.enums.LogLevel;
import br.com.codenation.centralerrosapi.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

		LogApplication application = new LogApplication().builder()
				.name("central-api-rest")
				.ip("127.0.0.1")
				.host("java-client-test")
                .environment(LogEnvironment.DEVELOPMENT)
				.build();

		List<Log> logs = new ArrayList<Log>();
		for (int i = 0; i < 25; i++) {
            LogDetail detail = new LogDetail().builder().timestamp(timestamp).level(LogLevel.ERROR).content("Detalhe do log " + i).build();
			Log log = new Log().builder().title("Título do log " + i).application(application).detail(detail).archived(false).build();
			logs.add(log);
		}

		logRepository.saveAll(logs);

	}
}
