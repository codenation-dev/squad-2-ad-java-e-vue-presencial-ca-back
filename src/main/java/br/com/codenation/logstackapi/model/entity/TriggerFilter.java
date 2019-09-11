package br.com.codenation.logstackapi.model.entity;

import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import br.com.codenation.logstackapi.model.enums.LogLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TriggerFilter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String appName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LogEnvironment environment;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LogLevel level;

}
