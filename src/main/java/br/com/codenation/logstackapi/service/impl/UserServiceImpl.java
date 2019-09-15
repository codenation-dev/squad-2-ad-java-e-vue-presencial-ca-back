package br.com.codenation.logstackapi.service.impl;

import br.com.codenation.logstackapi.dto.request.UserRequestDTO;
import br.com.codenation.logstackapi.exception.ResourceExistsException;
import br.com.codenation.logstackapi.mappers.UserMapper;
import br.com.codenation.logstackapi.model.entity.User;
import br.com.codenation.logstackapi.repository.UserRepository;
import br.com.codenation.logstackapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository repository;
    private UserMapper mapper;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User save(UserRequestDTO dto) {
        if (findByEmail(dto.getEmail()).isPresent()) {
            throw new ResourceExistsException("Email já cadastrado");
        }
        User user = mapper.map(dto);
        return repository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

}
