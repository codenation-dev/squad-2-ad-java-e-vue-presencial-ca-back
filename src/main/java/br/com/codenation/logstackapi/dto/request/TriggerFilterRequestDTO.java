package br.com.codenation.logstackapi.dto.request;

import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import br.com.codenation.logstackapi.model.enums.LogLevel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonPropertyOrder({"appName", "environment", "level"})
public class TriggerFilterRequestDTO {

    @ApiModelProperty(value = "Nome da aplicação", position = 1, example = "logstack-api", required = true)
    @Size(min = 1, max = 255)
    @NotNull
    private String appName;

    @ApiModelProperty(value = "Ambiente da aplicação", position = 2, example = "PRODUCTION", allowableValues = "DEVELOPMENT, TEST, PRODUCTION", required = true)
    @NotNull
    private LogEnvironment environment;

    @ApiModelProperty(value = "Nível do log", position = 3, example = "ERROR", allowableValues = "DEBUG, LEVEL, ENVIRONMENT", required = true)
    @NotNull
    private LogLevel level;

    @JsonIgnore
    public boolean isNull() {
        return Stream.of(appName, environment, level).allMatch(Objects::isNull);
    }

}
