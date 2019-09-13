package br.com.codenation.logstackapi.service.impl;

import br.com.codenation.logstackapi.dto.LogSearchDTO;
import br.com.codenation.logstackapi.exception.ResourceNotFoundException;
import br.com.codenation.logstackapi.model.entity.Log;
import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import br.com.codenation.logstackapi.model.enums.LogLevel;
import br.com.codenation.logstackapi.repository.LogRepository;
import br.com.codenation.logstackapi.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class LogServiceImpl implements LogService {

    private LogRepository repository;

    public List<Log> findAll() {
        return repository.findAll();
    }

    public Log findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Log not found with the specified id"));
    }

    public List<Log> findByTitle(String title) {
        return repository.findByTitleIgnoreCaseContaining(title);
    }

    public List<Log> findByIp(String ip) {
        return repository.findByApplicationIpContaining(ip);
    }

    public List<Log> findByLevel(LogLevel level) {
        return repository.findByDetailLevelIgnoreCaseContaining(level.toString());
    }

    public List<Log> findByApplicationName(String name) {
        return repository.findByApplicationNameIgnoreCaseContaining(name);
    }

    public List<Log> findByEnvironment(LogEnvironment environment) {
        return repository.findByEnvironmentIgnoreCaseContaining(environment.toString());
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

    public List<Log> find(LogSearchDTO search) {

        if (search.getTitle().isPresent()) return findByTitle(search.getTitle().get());
        if (search.getIp().isPresent()) return findByIp(search.getIp().get());
        if (search.getAppName().isPresent()) return findByApplicationName(search.getAppName().get());
        if (search.getEnvironment().isPresent()) return findByEnvironment(search.getEnvironment().get());
        if (search.getLevel().isPresent()) return findByLevel(search.getLevel().get());

        return findAll();
    }
}
