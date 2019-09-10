package br.com.codenation.logstackapi.builders;

import br.com.codenation.logstackapi.model.entity.Trigger;

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

    public Trigger build() {
        return trigger;
    }

}
