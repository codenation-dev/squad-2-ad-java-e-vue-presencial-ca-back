package br.com.codenation.logstackapi.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
public class UserRequestDTO {

    @ApiModelProperty(value = "Nome do usuário", example = "João da Codenation", required = true)
    @Size(min = 1, max = 120)
    @NotNull
    private String fullName;

    @ApiModelProperty(value = "Email do usuário", example = "joao@codenation.com", required = true)
    @Size(min = 8)
    @NotNull
    @Email
    private String email;

    @ApiModelProperty(value = "Senha do usuário", example = "teste@123", required = true)
    @NotNull
    private String password;

    @JsonIgnore
    public boolean isNull() {
        return Stream.of(fullName, email, password).allMatch(Objects::isNull);
    }
}
