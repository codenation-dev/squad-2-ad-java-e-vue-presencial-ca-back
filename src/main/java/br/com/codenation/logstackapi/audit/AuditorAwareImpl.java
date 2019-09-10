package br.com.codenation.logstackapi.audit;

import br.com.codenation.logstackapi.model.entity.User;
import br.com.codenation.logstackapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
import java.util.UUID;

public class AuditorAwareImpl implements AuditorAware<User> {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> getCurrentAuditor() {
        User user = User.builder().fullName("Administrador").email("admin2@example.com").password("teste@123").build();
        user = userRepository.save(user);
        return Optional.of(user);
    }
}
