package br.com.codenation.centralerrosapi.services;

import br.com.codenation.centralerrosapi.models.Error;
import br.com.codenation.centralerrosapi.repositories.ErrorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ErrorService {

    private ErrorRepository repository;

    public List<Error> listAll() {
        return repository.findAll();
    }

    public Error findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new NoResultException("Error not found with the specified id"));
    }
}
