package br.com.codenation.logstackapi.service;

import br.com.codenation.logstackapi.dto.AlertCreateDTO;
import br.com.codenation.logstackapi.mappers.AlertMapper;
import br.com.codenation.logstackapi.model.entity.Alert;
import br.com.codenation.logstackapi.repository.AlertRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AlertService {

    private AlertRepository repository;
    private AlertMapper mapper;

    public Alert save(AlertCreateDTO dto) {
        if (dto.isNull()) throw new IllegalArgumentException("Deve informar no mínimo uma das opções de filtro");
        Alert alert = mapper.map(dto);
        return repository.save(alert);
    }

}
