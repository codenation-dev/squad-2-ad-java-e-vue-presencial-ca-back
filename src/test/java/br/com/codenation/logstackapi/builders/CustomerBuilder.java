package br.com.codenation.logstackapi.builders;

import br.com.codenation.logstackapi.model.entity.Customer;

import java.time.LocalDateTime;
import java.util.UUID;

public class CustomerBuilder {

    private Customer customer;

    public static CustomerBuilder codenation() {
        CustomerBuilder builder = new CustomerBuilder();
        builder.customer = Customer.builder()
                .id(UUID.randomUUID())
                .apiKey(UUID.randomUUID())
                .user(UserBuilder.codenation().build())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        return builder;
    }

    public CustomerBuilder apiKey(UUID apiKey) {
        customer.setApiKey(apiKey);
        return this;
    }

    public Customer build() {
        return customer;
    }


}
