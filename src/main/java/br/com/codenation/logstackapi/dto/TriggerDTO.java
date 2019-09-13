package br.com.codenation.logstackapi.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"id", "name", "message", "createdBy", "isActive", "filters"})
public class TriggerDTO {

    @ApiModelProperty(value = "Identificador do gatilho", position = 1, example = "cbd9881e-88e9-4973-bfc0-5b4fcde29574")
    private UUID id;

    @ApiModelProperty(value = "Nome do gatilho", position = 2, example = "Level Erro em produção da API LogStack")
    private String name;

    @ApiModelProperty(value = "Mensagem para descrever o gatilho", position = 3, example = "Verificar com URGÊNCIA o serviço da aplicação")
    private String message;

    @ApiModelProperty(value = "Usuário criador", position = 4)
    private UserDTO createdBy;

    @ApiModelProperty(value = "Status do gatilho", position = 5)
    private Boolean isActive;

    @ApiModelProperty(value = "Filtros do gatilho", position = 6)
    private TriggerFilterDTO filters;

}