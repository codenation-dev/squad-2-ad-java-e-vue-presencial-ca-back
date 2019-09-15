package br.com.codenation.logstackapi.dto.response;

import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import br.com.codenation.logstackapi.model.enums.LogLevel;
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
@JsonPropertyOrder({"appName", "environment", "level"})
public class TriggerFilterResponseDTO {

    @ApiModelProperty(value = "Nome da aplicação", position = 1, example = "logstack-api")
    private String appName;

    @ApiModelProperty(value = "Ambiente da aplicação", position = 2, example = "PRODUCTION", allowableValues = "DEVELOPMENT, TEST, PRODUCTION", required = true)
    private LogEnvironment environment;

    @ApiModelProperty(value = "Nível do log", position = 3, example = "ERROR", allowableValues = "DEBUG, LEVEL, ENVIRONMENT", required = true)
    private LogLevel level;


}
