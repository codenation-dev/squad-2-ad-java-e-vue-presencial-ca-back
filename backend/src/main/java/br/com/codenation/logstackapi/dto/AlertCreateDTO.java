package br.com.codenation.logstackapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
public class AlertCreateDTO {

    @ApiModelProperty(value = "Descrição do alerta", example = "Erro em produção da API LogStack", required = true)
    @Size(min = 1, max = 255)
    @NotNull
    private String description;

    @ApiModelProperty(value = "Nome da aplicação", example = "logstack-api")
    @Size(min = 1, max = 255)
    @NotNull
    private String appName;

    @ApiModelProperty(value = "Ambiente", allowableValues = "DEVELOPMENT, TEST, PRODUCTION")
    @NotNull
    private String environment;

    @ApiModelProperty(value = "Level", allowableValues = "DEBUG, WARNING, ERROR")
    @NotNull
    private String level;

    public boolean isNull() {
        return Stream.of(appName, environment, level).allMatch(Objects::isNull);
    }

}
