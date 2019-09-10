package br.com.codenation.logstackapi.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TriggerCreateDTOTest {

    @Test
    public void deveRetornarDTONulo() {
        TriggerCreateDTO dto = new TriggerCreateDTO();
        assertThat(dto.isNull()).isTrue();
    }

    @Test
    public void deveRetornarDTOPreenchido() {
        TriggerCreateDTO dto = new TriggerCreateDTO();
        dto.setAppName("logstack-api");
        assertThat(dto.isNull()).isFalse();
    }
}
