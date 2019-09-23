package br.com.codenation.logstackapi.mapper;

import br.com.codenation.logstackapi.builders.LogBuilder;
import br.com.codenation.logstackapi.builders.TriggerBuilder;
import br.com.codenation.logstackapi.builders.TriggerRequestDTOBuilder;
import br.com.codenation.logstackapi.dto.request.LogRequestDTO;
import br.com.codenation.logstackapi.dto.request.TriggerRequestDTO;
import br.com.codenation.logstackapi.dto.response.LogResponseDTO;
import br.com.codenation.logstackapi.dto.response.TriggerResponseDTO;
import br.com.codenation.logstackapi.mappers.LogMapper;
import br.com.codenation.logstackapi.mappers.TriggerMapper;
import br.com.codenation.logstackapi.model.entity.Log;
import br.com.codenation.logstackapi.model.entity.Trigger;
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
public class TriggerMapperTest {

    @Autowired
    TriggerMapper triggerMapper;

    @Test
    public void dadoTrigger_quandoExecutarMapper_entaoDeveRetornarTriggerResponse(){
        Trigger trigger = TriggerBuilder.gatilho1().desarquivado().inativo().build();

        TriggerResponseDTO triggerResponse = triggerMapper.map(trigger);

        Assert.assertThat(triggerResponse, Matchers.notNullValue());
        Assert.assertThat(triggerResponse.getId(), Matchers.equalTo(trigger.getId()));
        Assert.assertThat(triggerResponse.getEmail(), Matchers.equalTo(trigger.getEmail()));
    }

    @Test
    public void dadoTriggerRequest_quandoExecutarMapper_entaoDeveRetornarTrigger(){
        TriggerRequestDTO triggerRequest = TriggerRequestDTOBuilder.gatilho1().build();

        Trigger trigger  = triggerMapper.map(triggerRequest);

        Assert.assertThat(trigger, Matchers.notNullValue());
        Assert.assertNotEquals(trigger.getMessage(), Matchers.equalTo(triggerRequest.getMessage()));
        Assert.assertThat(trigger.getName(), Matchers.equalTo(triggerRequest.getName()));
    }

    @Test
    public void dadoListaTrigger_quandoExecutarMapper_entaoDeveRetornarListaTriggerResponse(){
        List<Trigger> listaTrigger = new ArrayList<>();
        Trigger primeiraTrigger = TriggerBuilder.gatilho1().arquivado().ativo().build();
        Trigger segundaTrigger = TriggerBuilder.gatilho4().desarquivado().inativo().build();
        listaTrigger.add(primeiraTrigger);
        listaTrigger.add(segundaTrigger);

        List<TriggerResponseDTO> triggerResponse = triggerMapper.map(listaTrigger);

        Assert.assertThat(triggerResponse.size(), Matchers.equalTo(2));
        Assert.assertThat(triggerResponse.stream().filter(l -> l.getId().equals(primeiraTrigger.getId())).count(), Matchers.equalTo(1L));
    }
}
