package br.com.codenation.logstackapi.builders;

import br.com.codenation.logstackapi.model.entity.LogApplication;
import br.com.codenation.logstackapi.model.enums.LogEnvironment;

public class LogApplicationBuilder {

    private LogApplication logApplication;

    private LogApplicationBuilder() {
    }

    public static LogApplicationBuilder umaAplicacao() {
        LogApplicationBuilder builder = new LogApplicationBuilder();
        builder.logApplication = LogApplication.builder()
                .environment(LogEnvironment.DEVELOPMENT)
                .host("localhost")
                .ip("192.0.0.1")
                .name("logstack-api")
                .build();
        return builder;
    }

    public LogApplicationBuilder emDesenvolvimento() {
        logApplication.setEnvironment(LogEnvironment.DEVELOPMENT);
        return this;
    }

    public LogApplicationBuilder emTeste() {
        logApplication.setEnvironment(LogEnvironment.TEST);
        return this;
    }

    public LogApplicationBuilder emProducao() {
        logApplication.setEnvironment(LogEnvironment.PRODUCTION);
        return this;
    }

    public LogApplication build() {
        return logApplication;
    }

}
