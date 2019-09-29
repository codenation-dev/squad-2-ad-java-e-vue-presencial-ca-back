package br.com.codenation.logstackapi.service;

import br.com.codenation.logstackapi.dto.request.LogRequestDTO;
import br.com.codenation.logstackapi.exception.ResourceNotFoundException;
import br.com.codenation.logstackapi.mappers.LogMapper;
import br.com.codenation.logstackapi.model.entity.Customer;
import br.com.codenation.logstackapi.model.entity.Log;
import br.com.codenation.logstackapi.model.entity.LogSearch;
import br.com.codenation.logstackapi.repository.LogRepository;
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
public class LogService {

    private CustomerService customerService;
    private LogRepository logRepository;
    private LogMapper mapper;

    public Log findById(UUID id) {
        return logRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found with the specified id"));
    }

    public Log unarchive(UUID id) {
        Log log = findById(id);
        log.setArchived(Boolean.FALSE);
        return this.save(log);
    }

    public Log archive(UUID id) {
        Log log = findById(id);
        log.setArchived(Boolean.TRUE);
        return this.save(log);
    }

    public Log checkAlert(UUID id) {
        Log log = findById(id);
        log.setCheckAlert(Boolean.TRUE);
        return this.save(log);
    }

    public Log add(UUID apiKey, LogRequestDTO dto) {

        Customer customer = customerService.findByApiKey(apiKey)
                .orElseThrow(() -> new IllegalArgumentException("Invalid API Key"));

        Log log = mapper.map(dto);
        log.setCustomer(customer);
        log.setArchived(false);
        log.setCheckAlert(false);
        return logRepository.save(log);
    }

    public List<Log> findByCheckAlertNotVerified(Integer size) {
        if (size < 1) size = 10;
        return logRepository.findByCheckAlert(false).stream().limit(size).collect(Collectors.toList());
    }

    public Page<Log> find(LogSearch search, Integer page, Integer size, Sort sort) {

        search.validationValues();

        PageRequest pageRequest = PageRequest.of(page, size, sort);

        return logRepository.find(
                search.getTitle(),
                search.getAppName(),
                search.getHost(),
                search.getIp(),
                search.getEnvironment(),
                search.getContent(),
                search.getLevel(),
                search.getStartTimestamp(),
                search.getEndTimestamp(),
                search.getUser().getId(),
                pageRequest);

    }

    private Log save(Log log) {
        return logRepository.save(log);
    }

}
