package br.com.codenation.logstackapi.repository;

import br.com.codenation.logstackapi.model.entity.Alert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AlertRepository extends JpaRepository<Alert, UUID> {

    Page<Alert> findAll(Pageable pageable);

    @Query("select a from Alert a " +
            "where (:logId is null or a.log.id = :logId) " +
            "  and (:triggerId is null or a.trigger.id = :triggerId) ")
    Page<Alert> find(@Param("logId") UUID logId,
                     @Param("triggerId") UUID triggerId,
                     Pageable pageable);

}
