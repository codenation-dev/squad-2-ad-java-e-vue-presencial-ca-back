package br.com.codenation.logstackapi.builders;

import br.com.codenation.logstackapi.dto.request.LogApplicationRequestDTO;
import br.com.codenation.logstackapi.dto.request.LogRequestDTO;
import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import br.com.codenation.logstackapi.model.enums.LogLevel;

import java.time.LocalDateTime;

public class LogRequestDTOBuilder {

    private LogRequestDTO log;

    private LogRequestDTOBuilder() {
    }

    public static LogRequestDTOBuilder umLog() {

        LogApplicationRequestDTO application = LogApplicationRequestDTO.builder()
                .name("logstack-api")
                .environment(LogEnvironment.PRODUCTION)
                .ip("127.168.0.0")
                .host("api.herokuapp")
                .build();

        LogRequestDTOBuilder builder = new LogRequestDTOBuilder();
        builder.log = new LogRequestDTO();
        builder.log.setApplication(application);
        builder.log.setTitle("Título");
        builder.log.setContent("Descrição do log");
        builder.log.setTimestamp(LocalDateTime.now());
        builder.log.setLevel(LogLevel.ERROR);
        return builder;
    }

    public LogRequestDTO build() {
        return log;
    }

}
