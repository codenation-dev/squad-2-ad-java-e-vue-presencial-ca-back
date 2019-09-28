package br.com.codenation.logstackapi.service.impl;

import br.com.codenation.logstackapi.dto.request.UserRequestDTO;
import br.com.codenation.logstackapi.exception.ResourceExistsException;
import br.com.codenation.logstackapi.mappers.UserMapper;
import br.com.codenation.logstackapi.model.entity.User;
import br.com.codenation.logstackapi.repository.UserRepository;
import br.com.codenation.logstackapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private CustomerServiceImpl customerService;
    private UserRepository repository;
    private UserMapper mapper;
    private BCryptPasswordEncoder bCrypt;

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public User save(UserRequestDTO dto) {

        validEmailExists(dto.getEmail());

        User user = mapper.map(dto);
        user.setPassword(bCrypt.encode(dto.getPassword()));
        user = repository.saveAndFlush(user);

        customerService.save(user);

        return user;

    }

    @Override
    public User findById(UUID id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    private void validEmailExists(String email) {
        if (isEmailExists(email)) throw new ResourceExistsException("Email já cadastrado");
    }

    private Boolean isEmailExists(String email) {
        return repository.findByEmail(email).isPresent() ? true : false;
    }

    public User update(UUID id, UserRequestDTO dto) {
        User user = findById(id);
        User userAuth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!user.getId().equals(userAuth.getId())) {
            throw new IllegalArgumentException("Authenticated user is not the same as informed user");
        }

        if(!user.getEmail().equals(dto.getEmail())) {
            validEmailExists(dto.getEmail());
        }

        updateUser(user, dto);
        return repository.save(user);
    }

    private void updateUser(User user, UserRequestDTO dto) {
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPassword(bCrypt.encode(dto.getPassword()));
    }
}
