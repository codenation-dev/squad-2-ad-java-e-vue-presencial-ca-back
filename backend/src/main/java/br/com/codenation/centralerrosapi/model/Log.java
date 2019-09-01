package br.com.codenation.centralerrosapi.model;

import br.com.codenation.centralerrosapi.audit.Auditable;
import br.com.codenation.centralerrosapi.model.enums.Environment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.aspectj.lang.annotation.DeclareAnnotation;
import org.hibernate.annotations.Cascade;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Log extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String title;

    @Embedded
    private Server server;

    @NotNull
    private String application;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Environment environment;

    @OneToMany(mappedBy = "log")
    @JsonManagedReference
    private List<LogDetail> details;

    @NotNull
    private Boolean archived;

    public Integer getEvents() {
        return this.details.size();
    }

}
