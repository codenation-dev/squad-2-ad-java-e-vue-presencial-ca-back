package br.com.codenation.logstackapi.service;

import br.com.codenation.logstackapi.model.entity.Alert;
import br.com.codenation.logstackapi.model.entity.AlertSearch;
import org.springframework.data.domain.Page;

public interface AlertService {

    Page<Alert> find(AlertSearch search, Integer page, Integer size);

    Alert save(Alert alert);

}
