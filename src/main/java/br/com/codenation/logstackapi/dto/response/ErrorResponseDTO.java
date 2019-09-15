package br.com.codenation.logstackapi.dto.response;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"timestamp", "status", "error", "messsage", "path"})
public class ErrorResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Data atual", position = 1, example = "1568141529954")
    private Long timestamp;

    @ApiModelProperty(value = "Código HTTP Status", position = 2, example = "404")
    private Integer status;

    @ApiModelProperty(value = "Descrição do HTTP Status", position = 3, example = "Not Found")
    private String error;

    @ApiModelProperty(value = "Mensagem do erro", position = 4, example = "Log not found with the specified id")
    private String messsage;

    @ApiModelProperty(value = "Caminho da requisição", position = 5, example = "/api/v1/logs/7eec8504-1383-443f-85fe-724272034a61")
    private String path;

}