package br.com.codenation.logstackapi.builders;

import br.com.codenation.logstackapi.model.entity.LogSearch;
import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import br.com.codenation.logstackapi.model.enums.LogLevel;

import java.time.LocalDateTime;

public class LogSearchBuilder {
    private LogSearch log;

    private LogSearchBuilder() {
    }

    public static LogSearchBuilder umLog() {
        LogSearchBuilder builder = new LogSearchBuilder();
        builder.log = LogSearch.builder()
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

    public LogSearch build() {
        return log;
    }

}
