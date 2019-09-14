package br.com.codenation.logstackapi.service.impl;

import br.com.codenation.logstackapi.dto.LogCreateDTO;
import br.com.codenation.logstackapi.exception.ResourceNotFoundException;
import br.com.codenation.logstackapi.mappers.LogMapper;
import br.com.codenation.logstackapi.model.entity.Log;
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
    private LogMapper mapper;

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

    public List<Log> findByLevel(String level) {
        return repository.findByDetailLevelIgnoreCaseContaining(level);
    }

    public List<Log> findByApplicationName(String name) {
        return repository.findByApplicationNameIgnoreCaseContaining(name);
    }

    public List<Log> findByEnvironment(String environment) {
        return repository.findByEnvironmentIgnoreCaseContaining(environment);
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

    public Log save(LogCreateDTO dto){
        Log log = mapper.map(dto);
        log.setArchived(false);
        return repository.save(log);
    }
}
