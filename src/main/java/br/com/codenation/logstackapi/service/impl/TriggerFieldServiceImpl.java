package br.com.codenation.logstackapi.service.impl;

import br.com.codenation.logstackapi.dto.TriggerFilterCreateDTO;
import br.com.codenation.logstackapi.mappers.TriggerFilterMapper;
import br.com.codenation.logstackapi.model.entity.TriggerFilter;
import br.com.codenation.logstackapi.model.enums.TriggerFilterField;
import br.com.codenation.logstackapi.repository.TriggerFilterRepository;
import br.com.codenation.logstackapi.service.TriggerFilterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TriggerFieldServiceImpl implements TriggerFilterService {

    private TriggerFilterRepository repository;
    private TriggerFilterMapper mapper;

    @Override
    public TriggerFilter add(UUID id, TriggerFilterCreateDTO dto) {
        TriggerFilter triggerFilter = mapper.map(id, dto);
        return repository.save(triggerFilter);
    }

    @Override
    public List<TriggerFilter> add(UUID id, List<TriggerFilterCreateDTO> dtos) {
        List<TriggerFilter> filters = dtos.stream().map(filterDTO -> mapper.map(id, filterDTO)).collect(Collectors.toList());
        System.out.println(filters);
        return repository.saveAll(filters);
    }

    @Override
    public List<TriggerFilter> findAll() {
        return null;
    }

    @Override
    public Optional<TriggerFilter> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<TriggerFilter> findByIdAndField(UUID id, TriggerFilterField field) {
        return Optional.empty();
    }

    @Override
    public void delete(UUID id) {

    }
}
