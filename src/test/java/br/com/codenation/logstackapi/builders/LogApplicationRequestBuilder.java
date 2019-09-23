package br.com.codenation.logstackapi.builders;

import br.com.codenation.logstackapi.dto.request.LogApplicationRequestDTO;
import br.com.codenation.logstackapi.model.entity.LogApplication;
import br.com.codenation.logstackapi.model.enums.LogEnvironment;

public class LogApplicationRequestBuilder {

    private LogApplicationRequestDTO logApplicationRequestDTO;

    private LogApplicationRequestBuilder() {
    }

    public static LogApplicationRequestBuilder umaAplicacao() {
        LogApplicationRequestBuilder builder = new LogApplicationRequestBuilder();
        builder.logApplicationRequestDTO = LogApplicationRequestDTO.builder()
                .environment(LogEnvironment.DEVELOPMENT)
                .host("localhost")
                .ip("192.0.0.1")
                .name("logstack-api")
                .build();
        return builder;
    }

    public LogApplicationRequestBuilder emDesenvolvimento() {
        logApplicationRequestDTO.setEnvironment(LogEnvironment.DEVELOPMENT);
        return this;
    }

    public LogApplicationRequestBuilder emTeste() {
        logApplicationRequestDTO.setEnvironment(LogEnvironment.TEST);
        return this;
    }

    public LogApplicationRequestBuilder emProducao() {
        logApplicationRequestDTO.setEnvironment(LogEnvironment.PRODUCTION);
        return this;
    }

    public LogApplicationRequestDTO build() {
        return logApplicationRequestDTO;
    }
}
