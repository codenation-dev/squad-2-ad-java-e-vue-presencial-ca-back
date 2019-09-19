package br.com.codenation.logstackapi.builders;

import br.com.codenation.logstackapi.dto.request.TriggerFilterRequestDTO;
import br.com.codenation.logstackapi.dto.request.TriggerRequestDTO;
import br.com.codenation.logstackapi.dto.response.TriggerResponseDTO;
import br.com.codenation.logstackapi.model.entity.Trigger;

import java.util.UUID;

public class TriggerRequestDTOBuilder {

    private TriggerRequestDTO trigger;

    private TriggerRequestDTOBuilder() {
    }

    public static TriggerRequestDTOBuilder gatilho1() {
        TriggerRequestDTOBuilder builder = new TriggerRequestDTOBuilder();
        builder.trigger = TriggerRequestDTO.builder().filters(TriggerFilterRequestDTOBuilder.gatilho1().build()).message("message").name("Name").build();
        return builder;
    }

    public TriggerRequestDTO build() {
        return trigger;
    }
}
