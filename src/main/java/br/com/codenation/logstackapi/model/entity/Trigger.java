package br.com.codenation.logstackapi.model.entity;

import br.com.codenation.logstackapi.audit.Auditable;
import br.com.codenation.logstackapi.dto.UserDTO;
import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import br.com.codenation.logstackapi.model.enums.LogLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @Column(length = 99)
    private String appName;

    @Enumerated(EnumType.STRING)
    private LogEnvironment environment;

    @Enumerated(EnumType.STRING)
    private LogLevel level;

    @NotNull
    private Boolean active;

}
