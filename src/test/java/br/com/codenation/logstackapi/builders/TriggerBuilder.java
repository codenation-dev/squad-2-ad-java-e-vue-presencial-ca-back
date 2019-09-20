package br.com.codenation.logstackapi.builders;

import br.com.codenation.logstackapi.model.entity.Trigger;

import java.util.UUID;

public class TriggerBuilder {

    private Trigger trigger;

    private TriggerBuilder() {
    }

    public static TriggerBuilder gatilho1() {
        TriggerBuilder builder = new TriggerBuilder();
        builder.trigger = Trigger.builder().id(UUID.randomUUID()).name("Trigger 1 de demonstração").message("Trigger 1").build();
        return builder;
    }

    public static TriggerBuilder gatilho2() {
        TriggerBuilder builder = new TriggerBuilder();
        builder.trigger = Trigger.builder().id(UUID.randomUUID()).name("Trigger 2 de demonstração").message("Trigger 2").build();
        return builder;
    }

    public static TriggerBuilder gatilho3() {
        TriggerBuilder builder = new TriggerBuilder();
        builder.trigger = Trigger.builder().id(UUID.randomUUID()).name("Trigger 3 de demonstração").message("Trigger 3").build();
        return builder;
    }

    public static TriggerBuilder gatilho4() {
        TriggerBuilder builder = new TriggerBuilder();
        builder.trigger = Trigger.builder().id(UUID.randomUUID())
                .name("Trigger 4 de demonstração")
                .message("Message 4")
                .active(true)
                .archived(true)
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

    public TriggerBuilder arquivado() {
        trigger.setArchived(true);
        return this;
    }

    public TriggerBuilder desarquivado() {
        trigger.setArchived(false);
        return this;
    }

    public Trigger build() {
        return trigger;
    }

}