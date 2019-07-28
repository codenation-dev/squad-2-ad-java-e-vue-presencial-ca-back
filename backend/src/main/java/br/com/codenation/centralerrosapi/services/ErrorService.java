package br.com.codenation.centralerrosapi.services;

import br.com.codenation.centralerrosapi.models.Error;
import br.com.codenation.centralerrosapi.repositories.ErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrorService {

    @Autowired
    private ErrorRepository repository;

    public List<Error> listAll() {
        return repository.findAll();
    }

}
