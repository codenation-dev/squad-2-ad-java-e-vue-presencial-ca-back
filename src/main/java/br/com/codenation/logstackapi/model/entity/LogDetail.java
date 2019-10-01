package br.com.codenation.logstackapi.model.entity;

import br.com.codenation.logstackapi.model.enums.LogLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LogDetail {

    @NotNull
    private LocalDateTime timestamp;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private LogLevel level;

    @NotNull
    @Column(length = 5000)
    private String content;

}
