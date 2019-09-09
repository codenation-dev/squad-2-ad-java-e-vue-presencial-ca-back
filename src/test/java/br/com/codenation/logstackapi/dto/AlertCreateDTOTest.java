package br.com.codenation.logstackapi.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AlertCreateDTOTest {

    @Test
    public void deveRetornarDTONulo() {
        AlertCreateDTO dto = new AlertCreateDTO();
        assertThat(dto.isNull()).isTrue();
    }

    @Test
    public void deveRetornarDTOPreenchido() {
        AlertCreateDTO dto = new AlertCreateDTO();
        dto.setAppName("logstack-api");
        assertThat(dto.isNull()).isFalse();
    }
}
