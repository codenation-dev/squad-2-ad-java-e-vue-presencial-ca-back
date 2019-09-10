package br.com.codenation.logstackapi;

import br.com.codenation.logstackapi.model.entity.*;
import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import br.com.codenation.logstackapi.model.enums.LogLevel;
import br.com.codenation.logstackapi.repository.LogRepository;
import br.com.codenation.logstackapi.repository.TriggerRepository;
import br.com.codenation.logstackapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class LogStackApplication implements CommandLineRunner {

	@Autowired
	private LogRepository logRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TriggerRepository triggerRepository;

	public static void main(String[] args) {
        SpringApplication.run(LogStackApplication.class, args);
	}

	@Override
	public void run(String... args) {

		Trigger trigger = Trigger.builder()
				.name("Level Error em Produção")
				.environment(LogEnvironment.PRODUCTION)
				.level(LogLevel.ERROR)
				.active(true)
				.build();

		System.out.println("Trigger created -> " + trigger.toString());

		LocalDateTime timestamp = LocalDateTime.now();

		LogApplication application = new LogApplication().builder()
				.name("central-api-rest")
				.ip("127.0.0.1")
				.host("java-client-test")
                .environment(LogEnvironment.DEVELOPMENT)
				.build();

		List<Log> logs = new ArrayList<Log>();
		for (int i = 0; i < 25; i++) {
			LogDetail detail = new LogDetail().builder()
					.timestamp(timestamp)
					.level(LogLevel.ERROR)
					.content("Detalhe do log " + i)
					.build();
			Log log = new Log().builder()
					.title("Título do log " + i)
					.application(application)
					.detail(detail)
					.archived(false)
					.build();
			logs.add(log);
		}

		logRepository.saveAll(logs);
		triggerRepository.saveAll(Arrays.asList(trigger));
	}
}
