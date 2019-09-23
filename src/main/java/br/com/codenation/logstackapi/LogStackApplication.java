package br.com.codenation.logstackapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LogStackApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogStackApplication.class, args);
	}

}