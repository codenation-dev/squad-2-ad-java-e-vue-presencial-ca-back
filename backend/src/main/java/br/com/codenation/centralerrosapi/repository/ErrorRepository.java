package br.com.codenation.centralerrosapi.repository;

import br.com.codenation.centralerrosapi.model.Error;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ErrorRepository extends JpaRepository<Error, UUID> {
}
