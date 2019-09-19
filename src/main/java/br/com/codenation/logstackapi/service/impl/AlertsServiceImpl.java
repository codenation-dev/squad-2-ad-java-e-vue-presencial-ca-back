package br.com.codenation.logstackapi.service.impl;

import br.com.codenation.logstackapi.model.entity.Alerts;
import br.com.codenation.logstackapi.repository.AlertsRepository;
import br.com.codenation.logstackapi.service.AlertsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AlertsServiceImpl implements AlertsService {
    AlertsRepository repository;

    @Override
    public Page<Alerts> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Alerts save(Alerts alerts){
        return repository.save(alerts);
    }
}
