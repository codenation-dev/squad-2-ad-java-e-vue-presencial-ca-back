package br.com.codenation.centralerrosapi.model;

import br.com.codenation.centralerrosapi.model.enums.Environment;
import br.com.codenation.centralerrosapi.model.enums.Level;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Lob
    private String description;

    @NotNull
    private LocalDateTime date;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Level level;

    @NotNull
    private Integer events;

    @Embedded
    Server server;

}
