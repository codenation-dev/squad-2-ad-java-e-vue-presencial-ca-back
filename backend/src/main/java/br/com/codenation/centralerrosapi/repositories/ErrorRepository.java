package br.com.codenation.centralerrosapi.repositories;

import br.com.codenation.centralerrosapi.models.Error;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorRepository extends JpaRepository<Error, Integer> {
}
