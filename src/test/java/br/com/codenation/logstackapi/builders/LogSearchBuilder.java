package br.com.codenation.logstackapi.builders;

import br.com.codenation.logstackapi.model.entity.LogSearch;
import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import br.com.codenation.logstackapi.model.enums.LogLevel;

import java.time.LocalDateTime;

public class LogSearchBuilder {

    private LogSearch search;

    private LogSearchBuilder() {
    }

    public static LogSearchBuilder umLog() {
        LogSearchBuilder builder = new LogSearchBuilder();
        builder.search = LogSearch.builder()
                .appName("App Name")
                .environment(LogEnvironment.DEVELOPMENT)
                .host("hostname")
                .ip("1.0.2.10")
                .level(LogLevel.DEBUG)
                .title("Title")
                .user(UserBuilder.codenation().build())
                .startTimestamp(LocalDateTime.now())
                .endTimestamp(LocalDateTime.now())
                .build();
        return builder;
    }

    public LogSearch build() {
        return search;
    }

}
