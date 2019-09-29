package br.com.codenation.logstackapi.service;

import br.com.codenation.logstackapi.dto.request.TriggerRequestDTO;
import br.com.codenation.logstackapi.exception.ResourceNotFoundException;
import br.com.codenation.logstackapi.mappers.TriggerMapper;
import br.com.codenation.logstackapi.model.entity.Trigger;
import br.com.codenation.logstackapi.model.entity.TriggerSearch;
import br.com.codenation.logstackapi.model.entity.User;
import br.com.codenation.logstackapi.repository.TriggerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TriggerService {

    private TriggerRepository triggerRepository;
    private TriggerMapper mapper;

    public Trigger save(TriggerRequestDTO dto) {
        if (dto.isNull()) throw new IllegalArgumentException("Deve informar no mínimo uma das opções de filtro");
        Trigger trigger = mapper.map(dto);
        trigger.setArchived(false);
        trigger = triggerRepository.save(trigger);

        return trigger;
    }

    public List<Trigger> findAll() {
        return triggerRepository.findAll();
    }

    public List<Trigger> findByCreatedBy(User user) {
        return triggerRepository.findByCreatedById(user.getId());
    }

    public Trigger findById(UUID id) {
        return triggerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gatilho não encontrado"));
    }

    public List<Trigger> findByLog(TriggerSearch search) {
        return triggerRepository.findByActiveTrueAndArchivedFalseAndFiltersAppNameAndFiltersEnvironmentAndFiltersLevelAndCreatedById(
                search.getAppName(), search.getEnvironment(), search.getLevel(), search.getCreatedBy().getId());
    }

    public Trigger archive(UUID id) {
        Trigger trigger = findById(id);
        trigger.setArchived(true);
        trigger.setActive(false);
        return triggerRepository.save(trigger);
    }

    public Trigger unarchive(UUID id) {
        Trigger archive = findById(id);
        archive.setArchived(false);
        return triggerRepository.save(archive);
    }

    public Trigger inactive(UUID id) {
        Trigger active = findById(id);
        active.setActive(false);
        return triggerRepository.save(active);
    }

    public Trigger active(UUID id) {
        Trigger active = findById(id);
        active.setActive(true);
        return triggerRepository.save(active);
    }

    public Trigger update(UUID id, TriggerRequestDTO dto) {
        if (dto.isNull()) throw new IllegalArgumentException("Deve informar no mínimo uma das opções de filtro");
        Trigger trigger = findById(id);
        updateTrigger(trigger, dto);
        return triggerRepository.save(trigger);
    }

    private void updateTrigger(Trigger trigger, TriggerRequestDTO dto) {
        trigger.setName(dto.getName());
        trigger.setMessage(dto.getMessage());
        trigger.setEmail(dto.getEmail());
        trigger.setActive(dto.getIsActive());
        trigger.getFilters().setAppName(dto.getFilters().getAppName());
        trigger.getFilters().setEnvironment(dto.getFilters().getEnvironment());
        trigger.getFilters().setLevel(dto.getFilters().getLevel());
    }
}