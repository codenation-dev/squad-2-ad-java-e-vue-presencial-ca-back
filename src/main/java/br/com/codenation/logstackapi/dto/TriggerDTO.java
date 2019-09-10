package br.com.codenation.logstackapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class TriggerDTO {

    private UUID id;
    private String name;
    private String appName;
    private String environment;
    private String level;
    private UserDTO createdBy;
    private UserDTO lastModifiedBy;

}
