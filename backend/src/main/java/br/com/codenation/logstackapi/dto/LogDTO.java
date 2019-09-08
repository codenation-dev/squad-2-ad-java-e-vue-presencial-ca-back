package br.com.codenation.logstackapi.dto;

import br.com.codenation.logstackapi.model.entity.LogApplication;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class LogDTO {

    private UUID id;

    private String title;

    private LogApplication application;

    private Boolean archived;

    private LocalDateTime createdDate;

}
