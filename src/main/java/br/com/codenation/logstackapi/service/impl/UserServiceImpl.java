package br.com.codenation.logstackapi.service.impl;

import br.com.codenation.logstackapi.dto.request.UserRequestDTO;
import br.com.codenation.logstackapi.exception.ResourceExistsException;
import br.com.codenation.logstackapi.mappers.UserMapper;
import br.com.codenation.logstackapi.model.entity.User;
import br.com.codenation.logstackapi.repository.UserRepository;
import br.com.codenation.logstackapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private CustomerServiceImpl customerService;
    private UserRepository repository;
    private UserMapper mapper;
    private BCryptPasswordEncoder bCrypt;

    public List<User> findAll() {
        return repository.findAll();
    }

    @Transactional
    public User save(UserRequestDTO dto) {

        validEmailExists(dto.getEmail());

        User user = mapper.map(dto);
        user.setPassword(bCrypt.encode(dto.getPassword()));
        user = repository.saveAndFlush(user);

        customerService.save(user);

        return user;

    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    private void validEmailExists(String email) {
        if (isEmailExists(email)) throw new ResourceExistsException("Email já cadastrado");
    }

    private Boolean isEmailExists(String email) {
        return findByEmail(email).isPresent() ? true : false;
    }

}
