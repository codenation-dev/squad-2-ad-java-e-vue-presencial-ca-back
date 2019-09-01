package br.com.codenation.centralerrosapi.dto;

import br.com.codenation.centralerrosapi.model.Server;
import br.com.codenation.centralerrosapi.model.enums.Environment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class LogDTO {

    private UUID id;

    private String title;

    private Server server;

    private String application;

    private Environment environment;

    private Boolean archived;

    private Integer events;

    private LocalDateTime createdDate;

}
