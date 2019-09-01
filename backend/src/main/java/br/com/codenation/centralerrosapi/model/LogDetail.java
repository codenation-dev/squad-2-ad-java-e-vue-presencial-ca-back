package br.com.codenation.centralerrosapi.model;

import br.com.codenation.centralerrosapi.model.enums.Level;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class LogDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String line;

    @NotNull
    private LocalDateTime timestamp;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Level level;

    @ManyToOne
    @JsonBackReference
    private Log log;

}
