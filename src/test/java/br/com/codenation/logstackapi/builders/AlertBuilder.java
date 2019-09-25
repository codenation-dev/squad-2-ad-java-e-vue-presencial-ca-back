package br.com.codenation.logstackapi.builders;

import br.com.codenation.logstackapi.model.entity.Alert;

import java.util.UUID;

public class AlertBuilder {

    private Alert alert;

    private AlertBuilder() {
    }

    public static AlertBuilder umAlert() {
        AlertBuilder builder = new AlertBuilder();
        builder.alert = Alert.builder()
                .id(UUID.randomUUID())
                .log(LogBuilder.umLog().build())
                .trigger(TriggerBuilder.gatilho1().build())
                .build();
        return builder;
    }

    public static AlertBuilder doisAlert() {
        AlertBuilder builder = new AlertBuilder();
        builder.alert = Alert.builder()
                .id(UUID.randomUUID())
                .log(LogBuilder.umLog().emDesenvolvimento().arquivado().build())
                .trigger(TriggerBuilder.gatilho2().build())
                .build();
        return builder;
    }

    public Alert build() {
        return alert;
    }

}
