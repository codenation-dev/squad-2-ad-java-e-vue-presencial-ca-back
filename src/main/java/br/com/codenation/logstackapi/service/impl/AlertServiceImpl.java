package br.com.codenation.logstackapi.service.impl;

import br.com.codenation.logstackapi.model.entity.Alert;
import br.com.codenation.logstackapi.repository.AlertRepository;
import br.com.codenation.logstackapi.service.AlertService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AlertServiceImpl implements AlertService {
    AlertRepository repository;

    @Override
    public Page<Alert> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Alert save(Alert alert){
        return repository.save(alert);
    }
}
