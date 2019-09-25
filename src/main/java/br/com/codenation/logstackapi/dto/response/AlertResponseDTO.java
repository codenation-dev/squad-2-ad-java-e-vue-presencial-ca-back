package br.com.codenation.logstackapi.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"id", "trigger", "log", "createdDate"})
public class AlertResponseDTO {

    @ApiModelProperty(value = "Identificador do alerta", position = 1, example = "cbd9881e-88e9-4973-bfc0-5b4fcde29574")
    private UUID id;

    @ApiModelProperty(value = "Gatilho acionado", position = 2)
    private TriggerResponseDTO trigger;

    @ApiModelProperty(value = "Log acionado", position = 3)
    private LogResponseDTO log;

    @ApiModelProperty(value = "Data e hora do alerta", position = 4, example = "2019-09-16T19:14:06.738Z")
    private LocalDateTime createdDate;

}
