package br.com.codenation.logstackapi.service;

import br.com.codenation.logstackapi.model.entity.Customer;
import br.com.codenation.logstackapi.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> findAll();

    Optional<Customer> findByUser(User user);

    Customer save(User user);

}
