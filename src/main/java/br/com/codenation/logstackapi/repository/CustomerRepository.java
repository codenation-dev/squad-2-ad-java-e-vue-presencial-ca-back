package br.com.codenation.logstackapi.repository;

import br.com.codenation.logstackapi.model.entity.Customer;
import br.com.codenation.logstackapi.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Optional<Customer> findByUser(User user);

    Optional<Customer> findByApiKey(UUID apiKey);

}
