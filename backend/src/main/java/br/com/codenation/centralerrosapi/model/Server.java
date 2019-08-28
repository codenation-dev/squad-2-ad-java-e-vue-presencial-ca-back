package br.com.codenation.centralerrosapi.model;

import br.com.codenation.centralerrosapi.model.enums.Environment;
import br.com.codenation.centralerrosapi.model.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Server implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String host;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Environment environment;


}
