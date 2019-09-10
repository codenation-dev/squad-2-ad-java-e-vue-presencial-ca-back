package br.com.codenation.logstackapi.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"id", "title", "application", "level", "timestamp", "archived", "content", "createdBy"})
public class LogDetailDTO extends LogDTO {

    @ApiModelProperty(value = "Conteúdo do log", position = 7, example = "Initializing Spring DispatcherServlet 'dispatcherServlet'")
    private String content;

    @ApiModelProperty(value = "Usuário criador", position = 8)
    private UserDTO createdBy;

}