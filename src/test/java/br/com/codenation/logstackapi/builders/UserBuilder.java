package br.com.codenation.logstackapi.builders;

import br.com.codenation.logstackapi.model.entity.User;

import java.util.UUID;

public class UserBuilder {
    User user;

    public static UserBuilder admin() {
        UserBuilder builder = new UserBuilder();
        builder.user = User.builder()
                .id(UUID.randomUUID())
                .email("admin@admin.com")
                .fullName("Admin")
                .password("admin")
                .build();
        return builder;
    }

    public static UserBuilder codenation() {
        UserBuilder builder = new UserBuilder();
        builder.user = User.builder()
                .id(UUID.randomUUID())
                .email("codenation@hotmail.com")
                .fullName("Codenation")
                .password("codenation")
                .build();
        return builder;
    }

    public static UserBuilder squadnation() {
        UserBuilder builder = new UserBuilder();
        builder.user = User.builder()
                .id(UUID.randomUUID())
                .email("squadnation@hotmail.com")
                .fullName("Codenation")
                .password("codenation")
                .build();
        return builder;
    }

    public User build() {
        return user;
    }

}
