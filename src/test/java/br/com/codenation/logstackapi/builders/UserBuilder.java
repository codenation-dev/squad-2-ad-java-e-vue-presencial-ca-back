package br.com.codenation.logstackapi.builders;

import br.com.codenation.logstackapi.model.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

public class UserBuilder {

    private BCryptPasswordEncoder bCrypt;
    private User user;

    public static UserBuilder admin() {
        UserBuilder builder = new UserBuilder();
        builder.bCrypt = new BCryptPasswordEncoder();
        builder.user = User.builder()
                .id(UUID.randomUUID())
                .email("admin@admin.com")
                .fullName("Admin")
                .password(builder.bCrypt.encode(("admin")))
                .build();
        return builder;
    }

    public static UserBuilder codenation() {
        UserBuilder builder = new UserBuilder();
        builder.bCrypt = new BCryptPasswordEncoder();
        builder.user = User.builder()
                .id(UUID.randomUUID())
                .email("codenation@hotmail.com")
                .fullName("Codenation")
                .password(builder.bCrypt.encode(("codenation")))
                .build();
        return builder;
    }

    public static UserBuilder squadnation() {
        UserBuilder builder = new UserBuilder();
        builder.bCrypt = new BCryptPasswordEncoder();
        builder.user = User.builder()
                .id(UUID.randomUUID())
                .email("squadnation@hotmail.com")
                .fullName("Codenation")
                .password(builder.bCrypt.encode(("codenation")))
                .build();
        return builder;
    }

    public User build() {
        return user;
    }

}
