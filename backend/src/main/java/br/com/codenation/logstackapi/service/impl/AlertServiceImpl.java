package br.com.codenation.logstackapi.service.impl;

import br.com.codenation.logstackapi.dto.AlertCreateDTO;
import br.com.codenation.logstackapi.mappers.AlertMapper;
import br.com.codenation.logstackapi.model.entity.Alert;
import br.com.codenation.logstackapi.repository.AlertRepository;
import br.com.codenation.logstackapi.service.AlertService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AlertServiceImpl implements AlertService {

    private AlertRepository repository;
    private AlertMapper mapper;

    public Alert save(AlertCreateDTO dto) {
        if (dto.isNull()) throw new IllegalArgumentException("Deve informar no mínimo uma das opções de filtro");
        Alert alert = mapper.map(dto);
        alert.setActive(true);
        return repository.save(alert);
    }

    public List<Alert> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Alert> findById(UUID id) {
        return Optional.empty();
    }

}
