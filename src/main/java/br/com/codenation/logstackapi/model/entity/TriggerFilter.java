package br.com.codenation.logstackapi.model.entity;

import br.com.codenation.logstackapi.model.enums.TriggerFilterField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TriggerFilter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private TriggerFilterField field;
    private String value;

    @ManyToOne
    Trigger trigger;

}
