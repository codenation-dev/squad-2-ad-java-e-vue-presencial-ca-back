package br.com.codenation.logstackapi.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"id", "fullName", "email"})
public class UserResponseDTO {

    @ApiModelProperty(value = "Identificador do usuário", position = 1, example = "cbd9881e-88e9-4973-bfc0-5b4fcde29574")
    private UUID id;

    @ApiModelProperty(value = "Nome do usuário", position = 2, example = "Administrador do Sistema")
    private String fullName;

    @ApiModelProperty(value = "Email do usuário", position = 3, example = "admin@example.com")
    private String email;

}
