package br.com.codenation.logstackapi.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"id", "title", "application", "level", "level", "timestamp", "content", "archived", "createdBy", "lastModifiedBy"})
public class LogDetailDTO extends LogDTO {

    @ApiModelProperty(value = "Conteúdo do log", position = 1)
    private String content;

}
