package br.com.codenation.logstackapi.service;

import br.com.codenation.logstackapi.model.entity.Alert;
import br.com.codenation.logstackapi.model.entity.AlertSearch;
import br.com.codenation.logstackapi.model.entity.User;
import br.com.codenation.logstackapi.repository.AlertRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AlertService {

    private AlertRepository alertRepository;

    public Page<Alert> find(AlertSearch search, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return alertRepository.find(search.getLogId(), search.getTriggerId(), pageRequest);
    }

    public Alert save(Alert alert) {
        return alertRepository.save(alert);
    }

    public List<Alert> findByTriggerCreatedBy(User user) {
        return alertRepository.findByTriggerCreatedById(user.getId());
    }

    public Alert visualized(UUID id) {
        Alert alert = alertRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        alert.setVisualized(Boolean.TRUE);
        return alertRepository.save(alert);
    }

}
