package br.com.codenation.centralerrosapi.model;

import br.com.codenation.centralerrosapi.audit.Auditable;
import br.com.codenation.centralerrosapi.model.enums.Environment;
import br.com.codenation.centralerrosapi.model.enums.Level;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@ApiModel
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@JsonPropertyOrder({
        "id",
        "title",
        "details",
        "archived",
        "createdDate",
        "createdBy",
        "lastModifiedDate",
        "lastModifiedBy"
})
public class Log extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String title;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "log_id")
    private LogDetails details;

    @NotNull
    private Boolean archived;

}
