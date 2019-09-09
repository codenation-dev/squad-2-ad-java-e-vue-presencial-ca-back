package br.com.codenation.logstackapi.builders;

import br.com.codenation.logstackapi.model.entity.Alert;
import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import br.com.codenation.logstackapi.model.enums.LogLevel;

import java.util.UUID;

public class AlertBuilder {

    private Alert alert;

    private AlertBuilder() {
    }

    public static AlertBuilder umAlerta() {
        AlertBuilder builder = new AlertBuilder();
        builder.alert = Alert.builder()
                .id(UUID.randomUUID())
                .description("Alerta de demonstração")
                .appName("logstack-api")
                .build();
        return builder;
    }

    public AlertBuilder ativo() {
        alert.setActive(true);
        return this;
    }

    public AlertBuilder inativo() {
        alert.setActive(false);
        return this;
    }

    public AlertBuilder comLevelError() {
        alert.setLevel(LogLevel.ERROR);
        return this;
    }

    public AlertBuilder comLevelDebug() {
        alert.setLevel(LogLevel.DEBUG);
        return this;
    }

    public AlertBuilder comLevelWarning() {
        alert.setLevel(LogLevel.WARNING);
        return this;
    }

    public AlertBuilder emDesenvolvimento() {
        alert.setEnvironment(LogEnvironment.DEVELOPMENT);
        return this;
    }

    public AlertBuilder emTeste() {
        alert.setEnvironment(LogEnvironment.TEST);
        return this;
    }

    public AlertBuilder emProducao() {
        alert.setEnvironment(LogEnvironment.PRODUCTION);
        return this;
    }

    public Alert build() {
        return alert;
    }

}
