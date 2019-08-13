package br.com.codenation.centralerrosapi.services;

import br.com.codenation.centralerrosapi.models.Error;
import br.com.codenation.centralerrosapi.repositories.ErrorRepository;
import br.com.codenation.centralerrosapi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ErrorService {

    @Autowired
    private ErrorRepository repository;

    public List<Error> listAll() {
        return repository.findAll();
    }

    public Error findById(UUID id) {
        return repository.findById(id).orElseThrow(ObjectNotFoundException::new);
    }
}
