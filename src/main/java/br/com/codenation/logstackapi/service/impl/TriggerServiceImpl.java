package br.com.codenation.logstackapi.service.impl;

import br.com.codenation.logstackapi.dto.TriggerCreateDTO;
import br.com.codenation.logstackapi.mappers.TriggerMapper;
import br.com.codenation.logstackapi.model.entity.Trigger;
import br.com.codenation.logstackapi.model.entity.TriggerFilter;
import br.com.codenation.logstackapi.repository.TriggerRepository;
import br.com.codenation.logstackapi.service.TriggerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TriggerServiceImpl implements TriggerService {

    private TriggerRepository triggerRepository;
    private TriggerFieldServiceImpl triggerFieldService;
    private TriggerMapper mapper;

    @Transactional
    public Trigger save(TriggerCreateDTO dto) {
        if (dto.isNull()) throw new IllegalArgumentException("Deve informar no mínimo uma das opções de filtro");

        Trigger trigger = mapper.map(dto);
        trigger = triggerRepository.saveAndFlush(trigger);

        List<TriggerFilter> filters = triggerFieldService.add(trigger.getId(), dto.getFilters());
        trigger.setFilters(filters);

        return trigger;
    }

    public List<Trigger> findAll() {
        return triggerRepository.findAll();
    }

    @Override
    public Optional<Trigger> findById(UUID id) {
        return Optional.empty();
    }

}
