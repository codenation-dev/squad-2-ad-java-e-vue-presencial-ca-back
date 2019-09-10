package br.com.codenation.logstackapi.service.impl;

import br.com.codenation.logstackapi.dto.TriggerCreateDTO;
import br.com.codenation.logstackapi.mappers.TriggerMapper;
import br.com.codenation.logstackapi.model.entity.Trigger;
import br.com.codenation.logstackapi.repository.TriggerRepository;
import br.com.codenation.logstackapi.service.TriggerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TriggerServiceImpl implements TriggerService {

    private TriggerRepository repository;
    private TriggerMapper mapper;

    public Trigger save(TriggerCreateDTO dto) {
        if (dto.isNull()) throw new IllegalArgumentException("Deve informar no mínimo uma das opções de filtro");
        Trigger Trigger = mapper.map(dto);
        Trigger.setActive(true);
        return repository.save(Trigger);
    }

    public List<Trigger> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Trigger> findById(UUID id) {
        return Optional.empty();
    }

}
