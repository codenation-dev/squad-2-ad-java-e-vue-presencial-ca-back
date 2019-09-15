package br.com.codenation.logstackapi.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"id", "title", "application", "detail", "archived", "createdBy"})
public class LogResponseDTO {

    @ApiModelProperty(value = "Identificador do gatilho", position = 1, example = "cbd9881e-88e9-4973-bfc0-5b4fcde29574")
    private UUID id;

    @ApiModelProperty(value = "Título do log", position = 2, example = "o.s.web.servlet.DispatcherServlet")
    private String title;

    @ApiModelProperty(value = "Dados da aplicação", position = 3)
    private LogApplicationResponseDTO application;

    @ApiModelProperty(value = "Dados da aplicação", position = 4)
    private LogDetailResponseDTO detail;

    @ApiModelProperty(value = "Usuário criador", position = 6)
    private UserResponseDTO createdBy;

}
