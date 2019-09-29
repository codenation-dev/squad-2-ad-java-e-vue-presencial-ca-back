package br.com.codenation.logstackapi.builders;

import br.com.codenation.logstackapi.dto.request.TriggerRequestDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

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
                .isActive(false)
                .name("Name").build();
        return builder;
    }

    public static TriggerRequestDTOBuilder gatilho2() {
        TriggerRequestDTOBuilder builder = new TriggerRequestDTOBuilder();
        builder.trigger = TriggerRequestDTO.builder()
                .filters(TriggerFilterRequestDTOBuilder.gatilho1().build())
                .message("message")
                .email("email2@example.com")
                .isActive(false)
                .name("Name").build();
        return builder;
    }

    public TriggerRequestDTO build() {
        return trigger;
    }
}
