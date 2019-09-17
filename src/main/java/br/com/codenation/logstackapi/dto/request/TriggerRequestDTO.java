package br.com.codenation.logstackapi.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"name", "message", "filters"})
public class TriggerRequestDTO {

    @ApiModelProperty(value = "Nome do gatilho", position = 1, example = "Level Erro em produção da API LogStack", required = true)
    @Size(min = 1, max = 255)
    @NotNull
    private String name;

    @ApiModelProperty(value = "Mensagem para descrever o gatilho", position = 2, example = "Verificar com URGÊNCIA o serviço da aplicação", required = true)
    @Size(min = 1, max = 255)
    @NotNull
    private String message;

    @ApiModelProperty(value = "Filtros do gatilho", position = 3, required = true)
    @NotNull
    private TriggerFilterRequestDTO filters;

    @JsonIgnore
    public boolean isNull() {
        return Stream.of(name, message, filters).allMatch(Objects::isNull);
    }

}