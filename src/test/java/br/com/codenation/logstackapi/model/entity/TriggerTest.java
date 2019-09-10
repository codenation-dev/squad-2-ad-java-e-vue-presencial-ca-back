package br.com.codenation.logstackapi.model.entity;

import br.com.codenation.logstackapi.builders.TriggerBuilder;
import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import br.com.codenation.logstackapi.model.enums.LogLevel;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.*;

public class TriggerTest {

    @Test
    public void deveCriarTriggeraAtivoDeErroEmDesenvolvimento() {
        Trigger trigger = TriggerBuilder
                .umGatilho()
                .ativo()
                .comLevelError()
                .emDesenvolvimento()
                .build();

        assertThat(trigger.getId(), Matchers.notNullValue());
        assertTrue(trigger.getActive());
        assertEquals(trigger.getEnvironment(), LogEnvironment.DEVELOPMENT);
        assertEquals(trigger.getLevel(), LogLevel.ERROR);
    }

    @Test
    public void deveCriarTriggeraAtivoDeErroEmTeste() {
        Trigger trigger = TriggerBuilder
                .umGatilho()
                .ativo()
                .comLevelError()
                .emTeste()
                .build();

        assertThat(trigger.getId(), Matchers.notNullValue());
        assertTrue(trigger.getActive());
        assertEquals(trigger.getEnvironment(), LogEnvironment.TEST);
        assertEquals(trigger.getLevel(), LogLevel.ERROR);
    }

    @Test
    public void deveCriarTriggeraAtivoDeErroEmProducao() {
        Trigger trigger = TriggerBuilder
                .umGatilho()
                .ativo()
                .comLevelError()
                .emProducao()
                .build();

        assertThat(trigger.getId(), Matchers.notNullValue());
        assertTrue(trigger.getActive());
        assertEquals(trigger.getEnvironment(), LogEnvironment.PRODUCTION);
        assertEquals(trigger.getLevel(), LogLevel.ERROR);
    }


    @Test
    public void deveCriarTriggeraAtivoDeWarningEmDesenvolvimento() {
        Trigger trigger = TriggerBuilder
                .umGatilho()
                .ativo()
                .comLevelWarning()
                .emDesenvolvimento()
                .build();

        assertThat(trigger.getId(), Matchers.notNullValue());
        assertTrue(trigger.getActive());
        assertEquals(trigger.getEnvironment(), LogEnvironment.DEVELOPMENT);
        assertEquals(trigger.getLevel(), LogLevel.WARNING);
    }

    @Test
    public void deveCriarTriggeraAtivoDeWarningEmTeste() {
        Trigger trigger = TriggerBuilder
                .umGatilho()
                .ativo()
                .comLevelWarning()
                .emTeste()
                .build();

        assertThat(trigger.getId(), Matchers.notNullValue());
        assertTrue(trigger.getActive());
        assertEquals(trigger.getEnvironment(), LogEnvironment.TEST);
        assertEquals(trigger.getLevel(), LogLevel.WARNING);
    }


    @Test
    public void deveCriarTriggeraAtivoDeWarningEmProducao() {
        Trigger trigger = TriggerBuilder
                .umGatilho()
                .ativo()
                .comLevelWarning()
                .emProducao()
                .build();

        assertThat(trigger.getId(), Matchers.notNullValue());
        assertTrue(trigger.getActive());
        assertEquals(trigger.getEnvironment(), LogEnvironment.PRODUCTION);
        assertEquals(trigger.getLevel(), LogLevel.WARNING);
    }


    @Test
    public void deveCriarTriggeraAtivoDeDebugEmDesenvolvimento() {
        Trigger trigger = TriggerBuilder
                .umGatilho()
                .ativo()
                .comLevelDebug()
                .emDesenvolvimento()
                .build();

        assertThat(trigger.getId(), Matchers.notNullValue());
        assertTrue(trigger.getActive());
        assertEquals(trigger.getEnvironment(), LogEnvironment.DEVELOPMENT);
        assertEquals(trigger.getLevel(), LogLevel.DEBUG);
    }

    @Test
    public void deveCriarTriggeraAtivoDeDebugEmTeste() {
        Trigger trigger = TriggerBuilder
                .umGatilho()
                .ativo()
                .comLevelDebug()
                .emTeste()
                .build();

        assertThat(trigger.getId(), Matchers.notNullValue());
        assertTrue(trigger.getActive());
        assertEquals(trigger.getEnvironment(), LogEnvironment.TEST);
        assertEquals(trigger.getLevel(), LogLevel.DEBUG);
    }

    @Test
    public void deveCriarTriggeraAtivoDeDebugEmProducao() {
        Trigger trigger = TriggerBuilder
                .umGatilho()
                .ativo()
                .comLevelDebug()
                .emProducao()
                .build();

        assertThat(trigger.getId(), Matchers.notNullValue());
        assertTrue(trigger.getActive());
        assertEquals(trigger.getEnvironment(), LogEnvironment.PRODUCTION);
        assertEquals(trigger.getLevel(), LogLevel.DEBUG);
    }


}