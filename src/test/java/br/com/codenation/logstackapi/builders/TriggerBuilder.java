package br.com.codenation.logstackapi.builders;

import br.com.codenation.logstackapi.model.entity.Trigger;
import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import br.com.codenation.logstackapi.model.enums.LogLevel;

import java.util.UUID;

public class TriggerBuilder {

    private Trigger trigger;

    private TriggerBuilder() {
    }

    public static TriggerBuilder umGatilho() {
        TriggerBuilder builder = new TriggerBuilder();
        builder.trigger = Trigger.builder()
                .id(UUID.randomUUID())
                .name("Triggera de demonstração")
                .appName("logstack-api")
                .build();
        return builder;
    }

    public TriggerBuilder ativo() {
        trigger.setActive(true);
        return this;
    }

    public TriggerBuilder inativo() {
        trigger.setActive(false);
        return this;
    }

    public TriggerBuilder comLevelError() {
        trigger.setLevel(LogLevel.ERROR);
        return this;
    }

    public TriggerBuilder comLevelDebug() {
        trigger.setLevel(LogLevel.DEBUG);
        return this;
    }

    public TriggerBuilder comLevelWarning() {
        trigger.setLevel(LogLevel.WARNING);
        return this;
    }

    public TriggerBuilder emDesenvolvimento() {
        trigger.setEnvironment(LogEnvironment.DEVELOPMENT);
        return this;
    }

    public TriggerBuilder emTeste() {
        trigger.setEnvironment(LogEnvironment.TEST);
        return this;
    }

    public TriggerBuilder emProducao() {
        trigger.setEnvironment(LogEnvironment.PRODUCTION);
        return this;
    }

    public Trigger build() {
        return trigger;
    }

}
