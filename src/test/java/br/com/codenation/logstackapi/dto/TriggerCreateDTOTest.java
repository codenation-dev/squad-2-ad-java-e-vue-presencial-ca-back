package br.com.codenation.logstackapi.dto;

import br.com.codenation.logstackapi.model.enums.TriggerFilterField;
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
        TriggerFilterDTO filter = TriggerFilterDTO.builder().field(TriggerFilterField.APP_NAME).value("logstack-api").build();
        assertThat(dto.isNull()).isFalse();
    }
}