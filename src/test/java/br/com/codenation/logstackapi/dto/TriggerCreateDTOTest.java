package br.com.codenation.logstackapi.dto;

import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import br.com.codenation.logstackapi.model.enums.LogLevel;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TriggerCreateDTOTest {

    @Test
    public void deveRetornarDTONulo() {
        TriggerCreateDTO dto  = new TriggerCreateDTO();
        assertThat(dto.isNull()).isTrue();
    }

    @Test
    public void deveRetornarDTOPreenchido() {
        TriggerFilterCreateDTO filter = TriggerFilterCreateDTO.builder()
                .appName("logstack-api")
                .level(LogLevel.ERROR)
                .environment(LogEnvironment.PRODUCTION)
                .build();
        TriggerCreateDTO dto = new TriggerCreateDTO();
        dto.setFilters(filter);
        assertThat(dto.isNull()).isFalse();
    }
}