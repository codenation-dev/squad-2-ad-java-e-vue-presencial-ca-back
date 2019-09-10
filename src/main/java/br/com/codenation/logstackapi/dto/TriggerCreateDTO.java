package br.com.codenation.logstackapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"name", "filters"})
public class TriggerCreateDTO {

    @ApiModelProperty(value = "Nome do gatilho", position = 1, example = "Level Erro em produção da API LogStack", required = true)
    @Size(min = 1, max = 255)
    @NotNull
    private String name;

    @ApiModelProperty(value = "Filtros do gatilho", position = 2, required = true)
    @NotNull
    private List<TriggerFilterCreateDTO> filters;

    @JsonIgnore
    public boolean isNull() {
        return Stream.of(filters).allMatch(Objects::isNull);
    }

}
