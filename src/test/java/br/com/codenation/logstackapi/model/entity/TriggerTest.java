package br.com.codenation.logstackapi.model.entity;

import br.com.codenation.logstackapi.builders.TriggerBuilder;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.*;

public class TriggerTest {

    @Test
    public void deveCriarTriggeraAtivo() {
        Trigger trigger = TriggerBuilder.umGatilho().ativo().build();
        assertThat(trigger.getId(), Matchers.notNullValue());
        assertTrue(trigger.getActive());
    }

    @Test
    public void deveCriarTriggeraInativo() {
        Trigger trigger = TriggerBuilder.umGatilho().inativo().build();
        assertThat(trigger.getId(), Matchers.notNullValue());
        assertFalse(trigger.getActive());
    }



}