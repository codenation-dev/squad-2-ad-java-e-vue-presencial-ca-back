package br.com.codenation.centralerrosapi.model;

import br.com.codenation.centralerrosapi.model.enums.Environment;
import br.com.codenation.centralerrosapi.model.enums.Level;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@ApiModel
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LogDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Lob
    private String detail;

    @NotNull
    private String host;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Environment environment;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Level level;

    @NotNull
    private Integer events;

}
