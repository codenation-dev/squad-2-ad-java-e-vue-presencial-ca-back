package br.com.codenation.centralerrosapi.dto;

import br.com.codenation.centralerrosapi.model.LogDetail;
import br.com.codenation.centralerrosapi.model.Server;
import br.com.codenation.centralerrosapi.model.enums.Environment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class LogDetailDTO extends LogDTO {

    private List<LogDetail> details;

}
