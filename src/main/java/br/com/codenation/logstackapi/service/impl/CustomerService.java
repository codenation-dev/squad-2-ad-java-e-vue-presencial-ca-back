package br.com.codenation.logstackapi.service.impl;

import br.com.codenation.logstackapi.model.entity.Customer;
import br.com.codenation.logstackapi.model.entity.User;
import br.com.codenation.logstackapi.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findByUser(User user) {
        return customerRepository.findByUser(user);
    }

    public Optional<Customer> findByApiKey(UUID apiKey) {
        return customerRepository.findByApiKey(apiKey);
    }

    public Customer save(User user) {
        Customer customer = Customer.builder()
                .user(user)
                .apiKey(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        return customerRepository.save(customer);
    }

}
