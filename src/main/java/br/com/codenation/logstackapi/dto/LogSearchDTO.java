package br.com.codenation.logstackapi.dto;

import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import br.com.codenation.logstackapi.model.enums.LogLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogSearchDTO {

    private Optional<String> title;
    private Optional<String> ip;
    private Optional<String> appName;
    private Optional<LogLevel> level;
    private Optional<LogEnvironment> environment;

}
