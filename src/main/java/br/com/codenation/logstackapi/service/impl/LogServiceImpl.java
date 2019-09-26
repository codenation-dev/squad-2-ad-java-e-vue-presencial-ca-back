package br.com.codenation.logstackapi.service.impl;

import br.com.codenation.logstackapi.dto.request.LogRequestDTO;
import br.com.codenation.logstackapi.dto.request.LogSearchDTO;
import br.com.codenation.logstackapi.exception.ResourceNotFoundException;
import br.com.codenation.logstackapi.mappers.LogMapper;
import br.com.codenation.logstackapi.model.entity.Log;
import br.com.codenation.logstackapi.repository.LogRepository;
import br.com.codenation.logstackapi.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class LogServiceImpl implements LogService {

    private LogRepository repository;
    private LogMapper mapper;

    public Log findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found with the specified id"));
    }

    public Log unarchive(UUID id) {
        Log log = findById(id);
        log.setArchived(false);
        return repository.save(log);
    }

    public Log archive(UUID id) {
        Log log = findById(id);
        log.setArchived(true);
        return repository.save(log);
    }

    public Log checkAlert(UUID id, Boolean checkAlert) {
        Log log = findById(id);
        log.setCheckAlert(checkAlert);
        return repository.save(log);
    }

    public Log save(LogRequestDTO dto) {
        Log log = mapper.map(dto);
        log.setArchived(false);
        log.setCheckAlert(false);
        return repository.save(log);
    }

    public List<Log> findByCheckAlertNotVerified(Integer size) {
        if (size < 1) size = 10;
        return repository.findByCheckAlert(false).stream().limit(size).collect(Collectors.toList());
    }

    public Page<Log> find(LogSearchDTO search, Integer page, Integer size, Sort sort) {

        search.validationValues();

        PageRequest pageRequest = PageRequest.of(page, size, sort);

        return repository.find(
                search.getTitle(),
                search.getAppName(),
                search.getHost(),
                search.getIp(),
                search.getEnvironment(),
                search.getContent(),
                search.getLevel(),
                search.getStartTimestamp(),
                search.getEndTimestamp(),
                pageRequest);

    }
}
