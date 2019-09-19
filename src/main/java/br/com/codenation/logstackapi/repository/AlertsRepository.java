package br.com.codenation.logstackapi.repository;

import br.com.codenation.logstackapi.model.entity.Alerts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AlertsRepository extends JpaRepository<Alerts, UUID> {
    Page<Alerts> findAll(Pageable pageable);
}
