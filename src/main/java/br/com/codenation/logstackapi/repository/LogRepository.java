package br.com.codenation.logstackapi.repository;

import br.com.codenation.logstackapi.model.entity.Log;
import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import br.com.codenation.logstackapi.model.enums.LogLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface LogRepository extends JpaRepository<Log, UUID> {

    @Query("select l from Log l " +
            "where (:title is null or lower(l.title) like %:title%) " +
            "  and (:appName is null or lower(l.application.name) like %:appName%) " +
            "  and (:host is null or lower(l.application.host) like %:host%) " +
            "  and (:ip is null or lower(l.application.ip) like %:ip%) " +
            "  and (:environment is null or l.application.environment like :environment) " +
            "  and (:level is null or l.detail.level like :level) " +
            "  and (l.detail.timestamp between :start and :end) " +
            "  and (archived is false)")
    Page<Log> find(@Param("title") String title,
                   @Param("appName") String appName,
                   @Param("host") String host,
                   @Param("ip") String ip,
                   @Param("environment") LogEnvironment environment,
                   @Param("level") LogLevel level,
                   @Param("start") LocalDateTime startTimestamp,
                   @Param("end") LocalDateTime endTimestamp,
                   Pageable pageable);

}
