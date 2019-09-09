package br.com.codenation.logstackapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LogDetailDTO extends LogDTO {

    private String content;

}
