package br.com.codenation.logstackapi.builders;

import br.com.codenation.logstackapi.model.entity.LogDetail;
import br.com.codenation.logstackapi.model.enums.LogLevel;

import java.time.LocalDateTime;

public class LogDetailBuilder {

    private LogDetail logDetail;

    private LogDetailBuilder() {
    }

    public static LogDetailBuilder detalhe() {
        LogDetailBuilder builder = new LogDetailBuilder();
        builder.logDetail = LogDetail.builder()
                .level(LogLevel.ERROR)
                .content("Detalhes do log")
                .timestamp(LocalDateTime.of(2019, 8, 10, 10, 55, 32, 300))
                .build();
        return builder;
    }

    public LogDetailBuilder comLevelError() {
        logDetail.setLevel(LogLevel.ERROR);
        return this;
    }

    public LogDetailBuilder comLevelDebug() {
        logDetail.setLevel(LogLevel.DEBUG);
        return this;
    }

    public LogDetailBuilder comLevelWarning() {
        logDetail.setLevel(LogLevel.WARNING);
        return this;
    }

    public LogDetail build() {
        return logDetail;
    }

}
