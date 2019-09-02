package br.com.codenation.centralerrosapi.model;

import br.com.codenation.centralerrosapi.model.enums.Environment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogServer {

    private String hostname;

    private String ip;

    @NotNull
    private String application;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Environment environment;

}
