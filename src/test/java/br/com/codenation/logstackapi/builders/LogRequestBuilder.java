package br.com.codenation.logstackapi.builders;

import br.com.codenation.logstackapi.dto.request.LogRequestDTO;
import br.com.codenation.logstackapi.model.entity.Log;
import br.com.codenation.logstackapi.model.enums.LogLevel;

import java.util.UUID;

public class LogRequestBuilder {


    private LogRequestDTO log;

    private LogRequestBuilder() {
    }

    public static LogRequestBuilder umLog() {
        LogRequestBuilder builder = new LogRequestBuilder();
        builder.log = LogRequestDTO.builder()
                .title("Título")
                .application(LogApplicationRequestBuilder.umaAplicacao().build())
                .detail("Detalhe")
                .level(LogLevel.DEBUG)
                .build();
        return builder;
    }

    public LogRequestDTO build() {
        return log;
    }

}
