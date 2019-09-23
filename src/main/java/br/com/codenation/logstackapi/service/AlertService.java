package br.com.codenation.logstackapi.service;

import br.com.codenation.logstackapi.model.entity.Alert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlertService {

    Page<Alert> findAll(Pageable pageable);

    Alert save(Alert alert);

}
