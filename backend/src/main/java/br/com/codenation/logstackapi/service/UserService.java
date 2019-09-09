package br.com.codenation.logstackapi.service;

import br.com.codenation.logstackapi.dto.UserCreateDTO;
import br.com.codenation.logstackapi.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findByEmail(String email);

    User save(UserCreateDTO dto);

}
