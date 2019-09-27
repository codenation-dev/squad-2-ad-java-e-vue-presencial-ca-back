package br.com.codenation.logstackapi.mapper;

import br.com.codenation.logstackapi.builders.AlertBuilder;
import br.com.codenation.logstackapi.dto.response.AlertResponseDTO;
import br.com.codenation.logstackapi.mappers.AlertMapper;
import br.com.codenation.logstackapi.model.entity.Alert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AlertMapperTest {

    @Autowired
    private AlertMapper mapper;

    @Test
    public void dadoAlert_quandoExecutarMapper_entaoDeveRetornarAlertResponse(){
        Alert alert = AlertBuilder.umAlert().build();

        AlertResponseDTO alertResponse = mapper.map(alert);

        Assert.assertThat(alertResponse, Matchers.notNullValue());
        Assert.assertThat(alertResponse.getId(), Matchers.equalTo(alert.getId()));
        Assert.assertThat(alertResponse.getLog().getTitle(), Matchers.equalTo(alert.getLog().getTitle()));
    }

    @Test
    public void dadoListaAlerts_quandoExecutarMapper_entaoDeveRetornarListaAlertResponse(){
        List<Alert> listaAlert = new ArrayList<>();
        Alert primeiroAlert = AlertBuilder.umAlert().build();
        Alert segundoAlert = AlertBuilder.doisAlert().build();
        listaAlert.add(primeiroAlert);
        listaAlert.add(segundoAlert);

        List<AlertResponseDTO> alertResponseList = mapper.map(listaAlert);

        Assert.assertThat(alertResponseList.size(), Matchers.equalTo(2));
        Assert.assertThat(alertResponseList.stream().filter(l -> l.getId().equals(primeiroAlert.getId())).count(), Matchers.equalTo(1L));
    }
}
