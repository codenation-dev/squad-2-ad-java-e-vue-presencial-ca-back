package br.com.codenation.logstackapi.service.impl;

import br.com.codenation.logstackapi.builders.CustomerBuilder;
import br.com.codenation.logstackapi.builders.UserBuilder;
import br.com.codenation.logstackapi.mappers.CustomerMapper;
import br.com.codenation.logstackapi.model.entity.Customer;
import br.com.codenation.logstackapi.model.entity.User;
import br.com.codenation.logstackapi.repository.CustomerRepository;
import br.com.codenation.logstackapi.service.CustomerService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertThat;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerServiceImplTest {

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Test
    public void dadoUsuarioExistente_quandoPesquisarPorUsuario_entaoDeveEncontrarCustomer() {
        Customer customer = CustomerBuilder.codenation().build();
        User user = UserBuilder.codenation().build();

        Mockito.when(customerRepository.findByUser(user)).thenReturn(java.util.Optional.ofNullable(customer));

        Customer customerResponse = customerRepository.findByUser(user).orElse(null);

        assertThat(customerResponse, Matchers.notNullValue());
        assertThat(customerResponse.getUser().getFullName(), Matchers.equalTo(user.getFullName()));
        assertThat(customerResponse.getUser().getEmail(), Matchers.equalTo(user.getEmail()));
    }

    @Test
    public void dadoApiKey_quandoPesquisarPorApiKey_entaoDeveRetornarCustomer(){
        Customer customer = CustomerBuilder.codenation().build();

        Mockito.when(customerService.findByApiKey(customer.getApiKey())).thenReturn(java.util.Optional.of(customer));

        Customer customerResponse = customerService.findByApiKey(customer.getApiKey()).orElse(null);

        assertThat(customerResponse, Matchers.notNullValue());
        assertThat(customerResponse.getId(), Matchers.equalTo((customer.getId())));
    }

    @Test
    public void dadoApiKeyInexistente_quandoPesquisarPorApiKey_entaoDeveRetornarErro(){
        Customer customer = CustomerBuilder.codenation().build();

        Mockito.when(customerService.findByApiKey(customer.getApiKey())).thenReturn(java.util.Optional.of(customer));

        Customer customerResponse = customerService.findByApiKey(UUID.randomUUID()).orElse(null);

        assertThat(customerResponse, Matchers.nullValue());
    }


    @Test
    public void quandoPesquisarTodosCustomer_entaoDeveRetornarListaCustomer(){
        Customer umCustomer = CustomerBuilder.codenation().apiKey(UUID.randomUUID()).build();
        Customer doisCustomer = CustomerBuilder.codenation().apiKey(UUID.randomUUID()).build();
        List<Customer> listCustomer = new ArrayList<>();
        listCustomer.add(umCustomer);
        listCustomer.add(doisCustomer);

        Mockito.when(customerRepository.findAll()).thenReturn(listCustomer);

        List<Customer> listCustomerResponse = customerService.findAll();

        assertThat(listCustomerResponse.stream().count(), Matchers.equalTo(2L));
        assertThat(listCustomerResponse.stream().filter(c -> c.getApiKey().equals(umCustomer.getApiKey())).count(), Matchers.equalTo(1L));
        assertThat(listCustomerResponse.stream().filter(c -> c.getApiKey().equals(UUID.randomUUID())).count(), Matchers.equalTo(0L));
    }
}
