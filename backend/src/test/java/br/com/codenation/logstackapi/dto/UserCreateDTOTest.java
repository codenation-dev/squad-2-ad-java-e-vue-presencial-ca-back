package br.com.codenation.logstackapi.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserCreateDTOTest {

    @Test
    public void deveRetornarDTONulo() {
        UserCreateDTO dto = new UserCreateDTO();
        assertThat(dto.isNull()).isTrue();
    }

    @Test
    public void NaoDeveRetornarDTONulo() {
        UserCreateDTO dto = new UserCreateDTO();
        dto.setFullName("Nome");
        assertThat(dto.isNull()).isFalse();
    }
}
