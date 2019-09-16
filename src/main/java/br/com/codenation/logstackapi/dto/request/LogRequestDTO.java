package br.com.codenation.logstackapi.dto.request;

import br.com.codenation.logstackapi.model.enums.LogLevel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class LogRequestDTO {

    @ApiModelProperty(value = "Title", example = "Null pointer exception", required = true)
    @Size(min = 2, max = 120)
    @NotNull
    private String title;

    @ApiModelProperty(value = "Dados da aplicação")
    private LogApplicationRequestDTO application;

    @ApiModelProperty(value = "Nível do log", position = 4, example = "ERROR", allowableValues = "DEBUG, WARNING, ERROR")
    @NotNull
    private LogLevel level;

    @ApiModelProperty(value = "Timestamp", example = "2000-11-21 17:21:00", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    private LocalDateTime timestamp;

    @ApiModelProperty(value = "Detalhe do erro", example = "Fatal error on line 45", required = true)
    @NotNull
    private String detail;
}
