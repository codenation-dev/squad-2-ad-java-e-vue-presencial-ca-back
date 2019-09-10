package br.com.codenation.logstackapi.service;

import br.com.codenation.logstackapi.dto.TriggerFilterCreateDTO;
import br.com.codenation.logstackapi.model.entity.TriggerFilter;
import br.com.codenation.logstackapi.model.enums.TriggerFilterField;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TriggerFilterService {

    TriggerFilter add(UUID id, TriggerFilterCreateDTO dto);

    List<TriggerFilter> add(UUID id, List<TriggerFilterCreateDTO> dtos);

    List<TriggerFilter> findAll();

    Optional<TriggerFilter> findById(UUID id);

    Optional<TriggerFilter> findByIdAndField(UUID id, TriggerFilterField field);

    void delete(UUID id);

}
