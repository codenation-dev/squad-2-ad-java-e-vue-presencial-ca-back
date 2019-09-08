package br.com.codenation.centralerrosapi.dto;

import br.com.codenation.centralerrosapi.model.LogDetail;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LogDetailDTO extends LogDTO {

    private LogDetail detail;

}
