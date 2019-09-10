package br.com.codenation.logstackapi.model.entity;

import br.com.codenation.logstackapi.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Trigger extends Auditable<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(length = 99)
    private String name;

    @OneToMany(mappedBy = "trigger")
    private List<TriggerFilter> filters = new ArrayList<>();

    @NotNull
    @Builder.Default
    private Boolean active = true;

}