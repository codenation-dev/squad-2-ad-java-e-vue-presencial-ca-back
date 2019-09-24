package br.com.codenation.logstackapi.service;

import br.com.codenation.logstackapi.model.entity.Alert;
import org.springframework.data.domain.Page;

public interface AlertService {

    Page<Alert> find(Integer page, Integer size);

    Alert save(Alert alert);

}
