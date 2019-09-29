package br.com.codenation.logstackapi.model.entity;

import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import br.com.codenation.logstackapi.model.enums.LogLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TriggerSearch {

    private String appName;
    private LogEnvironment environment;
    private LogLevel level;
    private User createdBy;

}
