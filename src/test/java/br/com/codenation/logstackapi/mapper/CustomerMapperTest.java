package br.com.codenation.logstackapi.mapper;

import br.com.codenation.logstackapi.builders.CustomerBuilder;
import br.com.codenation.logstackapi.dto.response.CustomerResponseDTO;
import br.com.codenation.logstackapi.mappers.CustomerMapper;
import br.com.codenation.logstackapi.model.entity.Customer;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerMapperTest {

    @Autowired
    private CustomerMapper mapper;

    @Test
    public void dadoCustomer_quandoExecutarMapper_entaoDeveRetornarCustomerResponse(){
        Customer customer = CustomerBuilder.codenation().build();

        CustomerResponseDTO customerResponseDTO = mapper.map(customer);

        Assert.assertThat(customerResponseDTO, Matchers.notNullValue());
        Assert.assertThat(customerResponseDTO.getId(), Matchers.equalTo(customer.getId()));
        Assert.assertThat(customerResponseDTO.getApiKey(), Matchers.equalTo(customer.getApiKey()));
    }
}

