package br.com.codenation.logstackapi.dto;

import br.com.codenation.logstackapi.model.LogDetail;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LogDetailDTO extends LogDTO {

    private LogDetail detail;

}
