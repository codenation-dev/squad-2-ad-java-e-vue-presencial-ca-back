package br.com.codenation.logstackapi.dto;

import br.com.codenation.logstackapi.model.enums.TriggerFilterField;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonPropertyOrder({"name", "filters"})
public class TriggerFilterCreateDTO {

    @ApiModelProperty(value = "Campo do filtro", position = 1, example = "APP_NAME", allowableValues = "APP_NAME, LEVEL, ENVIRONMENT", required = true)
    private TriggerFilterField field;

    @ApiModelProperty(value = "Valor do Filtro", position = 2, example = "logstack-api", required = true)
    private String value;

}
