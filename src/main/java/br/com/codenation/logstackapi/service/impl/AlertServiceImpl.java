package br.com.codenation.logstackapi.service.impl;

import br.com.codenation.logstackapi.model.entity.Alert;
import br.com.codenation.logstackapi.model.entity.AlertSearch;
import br.com.codenation.logstackapi.repository.AlertRepository;
import br.com.codenation.logstackapi.service.AlertService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AlertServiceImpl implements AlertService {

    private AlertRepository repository;

    @Override
    public Page<Alert> find(AlertSearch search, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.find(search.getLogId(), search.getTriggerId(), pageRequest);
    }

    @Override
    public Alert save(Alert alert) {
        return repository.save(alert);
    }
}
