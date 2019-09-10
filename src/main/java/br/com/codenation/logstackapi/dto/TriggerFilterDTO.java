package br.com.codenation.logstackapi.dto;

import br.com.codenation.logstackapi.model.enums.TriggerFilterField;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonPropertyOrder({"id", "field", "value"})
public class TriggerFilterDTO {

    @ApiModelProperty(value = "Identificador do gatilho", position = 1, example = "cbd9881e-88e9-4973-bfc0-5b4fcde29574")
    private UUID id;

    @ApiModelProperty(value = "Campo do filtro", position = 2, example = "APP_NAME", allowableValues = "APP_NAME, LEVEL, ENVIRONMENT", required = true)
    private TriggerFilterField field;

    @ApiModelProperty(value = "Valor do Filtro", position = 3, example = "logstack-api", required = true)
    private String value;

}
