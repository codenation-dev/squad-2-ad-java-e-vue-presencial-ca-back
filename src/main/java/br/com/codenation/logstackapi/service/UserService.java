package br.com.codenation.logstackapi.service;

import br.com.codenation.logstackapi.dto.request.UserRequestDTO;
import br.com.codenation.logstackapi.model.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    List<User> findAll();

    Optional<User> findByEmail(String email);

    User save(UserRequestDTO dto);

    User findById(UUID id);

    User update(UUID id, UserRequestDTO dto);

}
