package br.com.codenation.logstackapi.model.entity;

import br.com.codenation.logstackapi.model.enums.LogEnvironment;
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
public class LogApplication {

    @NotNull
    @Column(name = "app_name")
    private String name;

    @NotNull
    @Column(name = "app_host")
    private String host;

    @NotNull
    @Column(name = "app_ip")
    private String ip;

    @NotNull
    @Column(name = "app_environment")
    @Enumerated(value = EnumType.STRING)
    private LogEnvironment environment;

}
