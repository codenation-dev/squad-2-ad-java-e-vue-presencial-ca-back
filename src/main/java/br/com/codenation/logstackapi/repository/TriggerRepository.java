package br.com.codenation.logstackapi.repository;

import br.com.codenation.logstackapi.model.entity.Trigger;
import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import br.com.codenation.logstackapi.model.enums.LogLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TriggerRepository extends JpaRepository<Trigger, UUID> {

    List<Trigger> findByActiveTrueAndArchivedFalseAndFiltersAppNameAndFiltersEnvironmentAndFiltersLevel(
            String appName, LogEnvironment environment, LogLevel level);
}

