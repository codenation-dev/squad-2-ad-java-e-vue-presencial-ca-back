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
import java.util.List;

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

		TriggerFilter filter = TriggerFilter.builder()
				.appName("logstack-api")
				.environment(LogEnvironment.PRODUCTION)
				.level(LogLevel.ERROR)
				.build();

		Trigger trigger = Trigger.builder()
				.name("Level Error em Produção na Aplicação LOGSTACK-API")
				.active(true)
				.filters(filter)
				.build();

		triggerRepository.save(trigger);

		LocalDateTime timestamp = LocalDateTime.now();

		LogApplication application = LogApplication.builder()
				.name("central-api-rest")
				.ip("127.0.0.1")
				.host("java-client-test")
				.environment(LogEnvironment.DEVELOPMENT)
				.build();

		List<Log> logs = new ArrayList<>();
		for (int i = 0; i < 25; i++) {
			LogDetail detail = LogDetail.builder()
					.timestamp(timestamp)
					.level(LogLevel.ERROR)
					.content("Detalhe do log " + i)
					.build();
			Log log = Log.builder()
					.title("Título do log " + i)
					.application(application)
					.detail(detail)
					.archived(false)
					.build();
			logs.add(log);
		}

		logRepository.saveAll(logs);
	}
}