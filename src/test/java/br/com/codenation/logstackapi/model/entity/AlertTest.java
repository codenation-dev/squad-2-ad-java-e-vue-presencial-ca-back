package br.com.codenation.logstackapi.model.entity;

import br.com.codenation.logstackapi.builders.AlertBuilder;
import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import br.com.codenation.logstackapi.model.enums.LogLevel;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.*;

public class AlertTest {

    @Test
    public void deveCriarAlertaAtivoDeErroEmDesenvolvimento() {
        Alert alert = AlertBuilder
                .umAlerta()
                .ativo()
                .comLevelError()
                .emDesenvolvimento()
                .build();

        assertThat(alert.getId(), Matchers.notNullValue());
        assertTrue(alert.getActive());
        assertEquals(alert.getEnvironment(), LogEnvironment.DEVELOPMENT);
        assertEquals(alert.getLevel(), LogLevel.ERROR);
    }

    @Test
    public void deveCriarAlertaAtivoDeErroEmTeste() {
        Alert alert = AlertBuilder
                .umAlerta()
                .ativo()
                .comLevelError()
                .emTeste()
                .build();

        assertThat(alert.getId(), Matchers.notNullValue());
        assertTrue(alert.getActive());
        assertEquals(alert.getEnvironment(), LogEnvironment.TEST);
        assertEquals(alert.getLevel(), LogLevel.ERROR);
    }

    @Test
    public void deveCriarAlertaAtivoDeErroEmProducao() {
        Alert alert = AlertBuilder
                .umAlerta()
                .ativo()
                .comLevelError()
                .emProducao()
                .build();

        assertThat(alert.getId(), Matchers.notNullValue());
        assertTrue(alert.getActive());
        assertEquals(alert.getEnvironment(), LogEnvironment.PRODUCTION);
        assertEquals(alert.getLevel(), LogLevel.ERROR);
    }


    @Test
    public void deveCriarAlertaAtivoDeWarningEmDesenvolvimento() {
        Alert alert = AlertBuilder
                .umAlerta()
                .ativo()
                .comLevelWarning()
                .emDesenvolvimento()
                .build();

        assertThat(alert.getId(), Matchers.notNullValue());
        assertTrue(alert.getActive());
        assertEquals(alert.getEnvironment(), LogEnvironment.DEVELOPMENT);
        assertEquals(alert.getLevel(), LogLevel.WARNING);
    }

    @Test
    public void deveCriarAlertaAtivoDeWarningEmTeste() {
        Alert alert = AlertBuilder
                .umAlerta()
                .ativo()
                .comLevelWarning()
                .emTeste()
                .build();

        assertThat(alert.getId(), Matchers.notNullValue());
        assertTrue(alert.getActive());
        assertEquals(alert.getEnvironment(), LogEnvironment.TEST);
        assertEquals(alert.getLevel(), LogLevel.WARNING);
    }


    @Test
    public void deveCriarAlertaAtivoDeWarningEmProducao() {
        Alert alert = AlertBuilder
                .umAlerta()
                .ativo()
                .comLevelWarning()
                .emProducao()
                .build();

        assertThat(alert.getId(), Matchers.notNullValue());
        assertTrue(alert.getActive());
        assertEquals(alert.getEnvironment(), LogEnvironment.PRODUCTION);
        assertEquals(alert.getLevel(), LogLevel.WARNING);
    }


    @Test
    public void deveCriarAlertaAtivoDeDebugEmDesenvolvimento() {
        Alert alert = AlertBuilder
                .umAlerta()
                .ativo()
                .comLevelDebug()
                .emDesenvolvimento()
                .build();

        assertThat(alert.getId(), Matchers.notNullValue());
        assertTrue(alert.getActive());
        assertEquals(alert.getEnvironment(), LogEnvironment.DEVELOPMENT);
        assertEquals(alert.getLevel(), LogLevel.DEBUG);
    }

    @Test
    public void deveCriarAlertaAtivoDeDebugEmTeste() {
        Alert alert = AlertBuilder
                .umAlerta()
                .ativo()
                .comLevelDebug()
                .emTeste()
                .build();

        assertThat(alert.getId(), Matchers.notNullValue());
        assertTrue(alert.getActive());
        assertEquals(alert.getEnvironment(), LogEnvironment.TEST);
        assertEquals(alert.getLevel(), LogLevel.DEBUG);
    }

    @Test
    public void deveCriarAlertaAtivoDeDebugEmProducao() {
        Alert alert = AlertBuilder
                .umAlerta()
                .ativo()
                .comLevelDebug()
                .emProducao()
                .build();

        assertThat(alert.getId(), Matchers.notNullValue());
        assertTrue(alert.getActive());
        assertEquals(alert.getEnvironment(), LogEnvironment.PRODUCTION);
        assertEquals(alert.getLevel(), LogLevel.DEBUG);
    }


}