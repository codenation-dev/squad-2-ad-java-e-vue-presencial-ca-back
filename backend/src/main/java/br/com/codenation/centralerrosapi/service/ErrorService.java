package br.com.codenation.centralerrosapi.service;

import br.com.codenation.centralerrosapi.model.Error;
import br.com.codenation.centralerrosapi.repository.ErrorRepository;
import br.com.codenation.centralerrosapi.service.exception.ResourceNotFoundException;
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
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Error not found with the specified id"));
    }

    public Error unarchive(UUID id) {
        Error error = findById(id);
        error.setArchived(false);
        return repository.save(error);
    }

}
