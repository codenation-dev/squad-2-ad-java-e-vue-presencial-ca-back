package br.com.codenation.logstackapi.service.impl;

import br.com.codenation.logstackapi.builders.AlertBuilder;
import br.com.codenation.logstackapi.model.entity.Alert;
import br.com.codenation.logstackapi.model.entity.AlertSearch;
import br.com.codenation.logstackapi.repository.AlertRepository;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AlertServiceTest {

    @Autowired
    private AlertService alertService;

    @MockBean
    private AlertRepository repository;

    @Test
    public void dadoAlert_quandoPesquisar_entaoDeveRetornarPageDeAlert(){
        List<Alert> alert = new ArrayList<>();
        alert.add(AlertBuilder.umAlert().build());
        alert.add(AlertBuilder.doisAlert().build());

        AlertSearch search = AlertSearch.builder().build();

        PageRequest pageRequest = PageRequest.of(1,3);
        Page<Alert> page = new PageImpl<>(alert);

        Mockito.when(repository.find(null, null, pageRequest)).thenReturn(page);

        Page<Alert> pageResponse = alertService.find(search, 1, 3);

        assertThat(pageResponse, Matchers.notNullValue());
        assertThat(pageResponse.getTotalElements(), Matchers.equalTo(2L));
        assertThat(pageResponse.stream().filter(c -> c.getTrigger().getMessage().equals("Trigger 1")).count(), Matchers.equalTo(1L));
    }

    @Test
    public void dadoAlert_quandoSalvar_entaoDeveRetornarAlert(){
        Alert alert = AlertBuilder.umAlert().build();

        Mockito.when(repository.save(alert)).thenReturn(alert);

        Alert alertResponse = alertService.save(alert);

        assertThat(alertResponse, Matchers.notNullValue());
        assertThat(alertResponse.getId(), Matchers.equalTo(alert.getId()));
        assertThat(alertResponse.getTrigger().getName(), Matchers.equalTo(alert.getTrigger().getName()));
    }

}
