package br.com.codenation.centralerrosapi.repository;

import br.com.codenation.centralerrosapi.model.LogDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LogDetailRepository extends JpaRepository<LogDetail, UUID> {
}
