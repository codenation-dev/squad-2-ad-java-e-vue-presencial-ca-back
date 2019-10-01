package br.com.codenation.logstackapi.model.entity;

import br.com.codenation.logstackapi.audit.Auditable;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "triggers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class Trigger extends Auditable<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(length = 99)
    private String name;

    @NotNull
    @Column(length = 255)
    private String message;

    @NotNull
    @Column(length = 255)
    @Email
    private String email;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "trigger_filter_id")
    private TriggerFilter filters;

    @NotNull
    private Boolean active;

    @OneToMany(mappedBy = "trigger")
    private List<Alert> alerts = new ArrayList<>();

    @NotNull
    private Boolean archived;

}