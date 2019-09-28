package br.com.codenation.logstackapi.service.impl;

import br.com.codenation.logstackapi.dto.request.LogRequestDTO;
import br.com.codenation.logstackapi.exception.ResourceNotFoundException;
import br.com.codenation.logstackapi.mappers.LogMapper;
import br.com.codenation.logstackapi.model.entity.Customer;
import br.com.codenation.logstackapi.model.entity.Log;
import br.com.codenation.logstackapi.model.entity.LogSearch;
import br.com.codenation.logstackapi.repository.LogRepository;
import br.com.codenation.logstackapi.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class LogServiceImpl implements LogService {

    private CustomerServiceImpl customerService;
    private LogRepository repository;
    private LogMapper mapper;

    @Override
    public Log findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found with the specified id"));
    }

    @Override
    public Log unarchive(UUID id) {
        Log log = findById(id);
        log.setArchived(Boolean.FALSE);
        return this.save(log);
    }

    @Override
    public Log archive(UUID id) {
        Log log = findById(id);
        log.setArchived(Boolean.TRUE);
        return this.save(log);
    }

    @Override
    public Log checkAlert(UUID id) {
        Log log = findById(id);
        log.setCheckAlert(Boolean.TRUE);
        return this.save(log);
    }

    @Override
    public Log add(UUID apiKey, LogRequestDTO dto) {

        Customer customer = customerService.findByApiKey(apiKey)
                .orElseThrow(() -> new IllegalArgumentException("Invalid API Key"));

        Log log = mapper.map(dto);
        log.setCustomer(customer);
        log.setArchived(false);
        log.setCheckAlert(false);
        return repository.save(log);
    }

    @Override
    public List<Log> findByCheckAlertNotVerified(Integer size) {
        if (size < 1) size = 10;
        return repository.findByCheckAlert(false).stream().limit(size).collect(Collectors.toList());
    }

    @Override
    public Page<Log> find(LogSearch search, Integer page, Integer size, Sort sort) {

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

    private Log save(Log log) {
        log.setUpdatedDate(LocalDateTime.now());
        return repository.save(log);
    }

}
