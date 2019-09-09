package br.com.codenation.logstackapi.service;

import br.com.codenation.logstackapi.dto.UserCreateDTO;
import br.com.codenation.logstackapi.model.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User save(UserCreateDTO dto);

}
