package br.com.codenation.centralerrosapi.model;

import br.com.codenation.centralerrosapi.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@ApiModel
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Error extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String title;

    @NotNull
    @Lob
    private String detail;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private ErrorEnvironment environment;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private ErrorLevel level;

    @NotNull
    private Integer events;

    @NotNull
    private Boolean archived;

}
