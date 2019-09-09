package br.com.codenation.logstackapi.service;

import br.com.codenation.logstackapi.dto.AlertCreateDTO;
import br.com.codenation.logstackapi.model.entity.Alert;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AlertService {

    Alert save(AlertCreateDTO dto);

    List<Alert> findAll();

    Optional<Alert> findById(UUID id);


}
