package br.com.codenation.logstackapi.service;

import br.com.codenation.logstackapi.model.entity.Alerts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlertsService {
    Page<Alerts> findAll(Pageable pageable);
}
