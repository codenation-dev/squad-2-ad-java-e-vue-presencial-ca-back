package br.com.codenation.centralerrosapi.service;

import br.com.codenation.centralerrosapi.model.User;
import br.com.codenation.centralerrosapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

}
