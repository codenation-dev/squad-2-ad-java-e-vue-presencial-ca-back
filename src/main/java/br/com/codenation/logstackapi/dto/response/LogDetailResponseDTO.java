package br.com.codenation.logstackapi.dto.response;

import br.com.codenation.logstackapi.model.enums.LogLevel;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"timestamp", "level", "content"})
public class LogDetailResponseDTO {

    @ApiModelProperty(value = "Data e hora do acionamento do log", position = 1, example = "2019-09-16T19:14:06.738Z")
    private LocalDateTime timestamp;

    @ApiModelProperty(value = "Nível do log", position = 2, example = "ERROR", allowableValues = "DEBUG, WARNING, ERROR")
    private LogLevel level;

    @ApiModelProperty(value = "Conteúdo do log", position = 3, example = "Initializing Spring DispatcherServlet 'dispatcherServlet'")
    private String content;

}
