package br.com.codenation.logstackapi.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"id", "name", "createdBy", "isActive", "filters"})
public class TriggerDTO {

    @ApiModelProperty(value = "Identificador do gatilho", position = 1, example = "cbd9881e-88e9-4973-bfc0-5b4fcde29574")
    private UUID id;

    @ApiModelProperty(value = "Nome do gatilho", position = 2, example = "Level Erro em produção da API LogStack")
    private String name;

    @ApiModelProperty(value = "Usuário criador", position = 3)
    private UserDTO createdBy;

    @ApiModelProperty(value = "Status do gatilho", position = 4)
    private Boolean isActive;

    @ApiModelProperty(value = "Filtros do gatilho", position = 5)
    private TriggerFilterDTO filters;

}