package br.com.codenation.logstackapi.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CustomerResponseDTO {

    private UUID id;

    private UUID apiKey;

    private UserResponseDTO user;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

}
