package br.com.codenation.logstackapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String title;

    @Embedded
    private LogApplication application;

    @Embedded
    private LogDetail detail;

    @NotNull
    private Boolean checkAlert;

    @NotNull
    private Boolean archived;

    @NotNull
    private LocalDateTime createdDate;

    @NotNull
    private LocalDateTime updatedDate;

    @NotNull
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
