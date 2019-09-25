package br.com.codenation.logstackapi.builders;

import br.com.codenation.logstackapi.dto.request.LogSearchDTO;
import br.com.codenation.logstackapi.model.entity.Log;
import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import br.com.codenation.logstackapi.model.enums.LogLevel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class LogSearchBuilder {
    private LogSearchDTO log;

    private LogSearchBuilder() {
    }

    public static LogSearchBuilder umLog() {
        LogSearchBuilder builder = new LogSearchBuilder();
        builder.log = LogSearchDTO.builder()
                .appName("App Name")
                .environment(LogEnvironment.DEVELOPMENT)
                .host("hostname")
                .ip("1.0.2.10")
                .level(LogLevel.DEBUG)
                .title("Title")
                .startTimestamp(LocalDateTime.now())
                .endTimestamp(LocalDateTime.now())
                .build();
        return builder;
    }

    public LogSearchDTO build() {
        return log;
    }

}
