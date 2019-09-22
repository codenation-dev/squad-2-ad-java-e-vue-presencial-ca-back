package br.com.codenation.logstackapi.builders;

import br.com.codenation.logstackapi.dto.request.UserRequestDTO;

public class UserResquestBuilder {

    private UserRequestDTO user;

    private UserResquestBuilder() {
    }

    public static UserResquestBuilder usuarioAdmin() {
        UserResquestBuilder builder = new UserResquestBuilder();
        builder.user = new UserRequestDTO();
        builder.user.setFullName("Usuário Administrador");
        builder.user.setEmail("admin@admin.com");
        builder.user.setPassword("admin");
        return builder;
    }

    public static UserResquestBuilder usuarioComum() {
        UserResquestBuilder builder = new UserResquestBuilder();
        builder.user = new UserRequestDTO();
        builder.user.setFullName("Usuário Comum");
        builder.user.setEmail("comum@example.com");
        builder.user.setPassword("1234");
        return builder;
    }

    public UserRequestDTO build() {
        return user;
    }

}