package br.com.codenation.logstackapi.model.entity;

import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"name", "host", "ip", "environment"})
public class LogApplication {

    @NotNull
    @Column(name = "app_name")
    @ApiModelProperty(
            value = "Nome da aplicação",
            position = 1,
            example = "logstack-api",
            required = true
    )
    private String name;

    @NotNull
    @Column(name = "app_host")
    @ApiModelProperty(
            value = "Hostname da aplicação",
            position = 2,
            example = "logstack-api.herokuapp.com",
            required = true
    )
    private String host;

    @NotNull
    @Column(name = "app_ip")
    @ApiModelProperty(
            value = "Ip da aplicação",
            position = 3,
            example = "184.456.41.11",
            required = true
    )
    private String ip;

    @NotNull
    @Column(name = "app_environment")
    @Enumerated(value = EnumType.STRING)
    @ApiModelProperty(
            value = "Ambiente da aplicação",
            position = 4, example = "PRODUCTION",
            allowableValues = "DEVELOPMENT, TEST, PRODUCTION",
            required = true
    )
    private LogEnvironment environment;

}
