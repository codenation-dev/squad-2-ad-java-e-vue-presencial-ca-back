package br.com.codenation.logstackapi.builders;

import br.com.codenation.logstackapi.model.entity.Trigger;
import br.com.codenation.logstackapi.model.entity.TriggerFilter;
import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import br.com.codenation.logstackapi.model.enums.LogLevel;

import java.util.UUID;

public class TriggerBuilder {

    private Trigger trigger;

    private TriggerBuilder() {
    }

    public static TriggerBuilder gatilho1() {

        TriggerFilter filter = TriggerFilter.builder()
                .appName("logstack-api")
                .environment(LogEnvironment.PRODUCTION)
                .level(LogLevel.ERROR)
                .build();

        TriggerBuilder builder = new TriggerBuilder();
        builder.trigger = Trigger.builder()
                .id(UUID.randomUUID())
                .name("Trigger 1 de demonstração")
                .message("Trigger 1")
                .email("email@example1.com")
                .filters(filter)
                .build();
        return builder;
    }

    public static TriggerBuilder gatilho2() {
        TriggerBuilder builder = new TriggerBuilder();
        builder.trigger = Trigger.builder()
                .id(UUID.randomUUID())
                .name("Trigger 2 de demonstração")
                .message("Trigger 2")
                .email("email@example2.com")
                .build();
        return builder;
    }

    public static TriggerBuilder gatilho3() {
        TriggerBuilder builder = new TriggerBuilder();
        builder.trigger = Trigger.builder()
                .id(UUID.randomUUID())
                .name("Trigger 3 de demonstração")
                .message("Trigger 3")
                .email("email@example3.com")
                .build();
        return builder;
    }

    public static TriggerBuilder gatilho4() {
        TriggerBuilder builder = new TriggerBuilder();
        builder.trigger = Trigger.builder().id(UUID.randomUUID())
                .name("Trigger 4 de demonstração")
                .message("Message 4")
                .email("email@example4.com")
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