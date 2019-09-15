package br.com.codenation.logstackapi.dto.response;

import br.com.codenation.logstackapi.model.enums.LogLevel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"id", "title", "application", "level", "timestamp", "archived"})
public class LogResponseDTO {

    @ApiModelProperty(value = "Identificador do gatilho", position = 1, example = "cbd9881e-88e9-4973-bfc0-5b4fcde29574")
    private UUID id;

    @ApiModelProperty(value = "Título do log", position = 2, example = "o.s.web.servlet.DispatcherServlet")
    private String title;

    @ApiModelProperty(value = "Dados da aplicação", position = 3)
    private LogApplicationResponseDTO application;

    @ApiModelProperty(value = "Nível do log", position = 4, example = "ERROR", allowableValues = "DEBUG, WARNING, ERROR")
    private LogLevel level;

    @ApiModelProperty(value = "Data e hora do acionamento do log", position = 5, example = "2019-09-10 14:40:01")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    @ApiModelProperty(value = "Log arquivado?", position = 6, example = "false")
    private Boolean archived;

}
