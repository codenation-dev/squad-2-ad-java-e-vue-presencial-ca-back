package br.com.codenation.logstackapi.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"id", "name", "filters", "isActive"})
public class TriggerDTO {

    @ApiModelProperty(value = "Identificador do gatilho", position = 1, example = "cbd9881e-88e9-4973-bfc0-5b4fcde29574")
    private UUID id;

    @ApiModelProperty(value = "Nome do gatilho", position = 2, example = "Level Erro em produção da API LogStack")
    private String name;

    @ApiModelProperty(value = "Filtros do gatilho", position = 3)
    private List<TriggerFilterDTO> filters;

    @ApiModelProperty(value = "Status do gatilho", position = 4)
    private Boolean isActive;

}
