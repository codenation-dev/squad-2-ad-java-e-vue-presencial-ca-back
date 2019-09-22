package br.com.codenation.logstackapi.builders;

import br.com.codenation.logstackapi.dto.request.TriggerRequestDTO;

public class TriggerRequestDTOBuilder {

    private TriggerRequestDTO trigger;

    private TriggerRequestDTOBuilder() {
    }

    public static TriggerRequestDTOBuilder gatilho1() {
        TriggerRequestDTOBuilder builder = new TriggerRequestDTOBuilder();
        builder.trigger = TriggerRequestDTO.builder()
                .filters(TriggerFilterRequestDTOBuilder.gatilho1().build())
                .message("message")
                .email("email@example.com")
                .name("Name").build();
        return builder;
    }

    public TriggerRequestDTO build() {
        return trigger;
    }
}
