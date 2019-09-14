package br.com.codenation.logstackapi.repository;

import br.com.codenation.logstackapi.model.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LogRepository extends JpaRepository<Log, UUID> {

    List<Log> findByTitleIgnoreCaseContaining(String title);

    List<Log> findByApplicationIpContaining(String ip);

    List<Log> findByApplicationNameIgnoreCaseContaining(String name);

    @Query("select l from Log l where level like %?1%")
    List<Log> findByDetailLevelIgnoreCaseContaining(@Param("level") String level);

    @Query("select l from Log l where app_environment like %?1%")
    List<Log> findByEnvironmentIgnoreCaseContaining(@Param("environment") String environment);

}
