package br.com.codenation.logstackapi.service;

import br.com.codenation.logstackapi.model.entity.Customer;
import br.com.codenation.logstackapi.model.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    List<Customer> findAll();

    Optional<Customer> findByUser(User user);

    Optional<Customer> findByApiKey(UUID apiKey);

    Customer save(User user);

}
