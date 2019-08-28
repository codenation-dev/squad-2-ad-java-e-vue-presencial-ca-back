package br.com.codenation.centralerrosapi.service;

import br.com.codenation.centralerrosapi.model.Log;
import br.com.codenation.centralerrosapi.repository.LogRepository;
import br.com.codenation.centralerrosapi.service.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class LogService {

    private LogRepository repository;

    public Page<Log> listAll(Integer page, Integer size, String sort) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, sort);
        return repository.findAll(pageRequest);
    }

    public Log findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Log not found with the specified id"));
    }

    public Log unarchive(UUID id) {
        Log error = findById(id);
        error.setArchived(false);
        return repository.save(error);
    }

    public Log archive(UUID id) {
        Log error = findById(id);
        error.setArchived(true);
        return repository.save(error);
    }
}
