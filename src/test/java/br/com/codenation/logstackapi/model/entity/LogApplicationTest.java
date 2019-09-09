package br.com.codenation.logstackapi.model.entity;

import br.com.codenation.logstackapi.builders.LogApplicationBuilder;
import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LogApplicationTest {

    @Test
    public void deveCriarLogApplicationEmDesenvolvimento() {
        LogApplication application = LogApplicationBuilder.umaAplicacao().emDesenvolvimento().build();
        assertEquals(application.getEnvironment(), LogEnvironment.DEVELOPMENT);
    }

    @Test
    public void deveCriarLogApplicationEmTeste() {
        LogApplication application = LogApplicationBuilder.umaAplicacao().emTeste().build();
        assertEquals(application.getEnvironment(), LogEnvironment.TEST);
    }

    @Test
    public void deveCriarLogApplicationEmProducao() {
        LogApplication application = LogApplicationBuilder.umaAplicacao().emProducao().build();
        assertEquals(application.getEnvironment(), LogEnvironment.PRODUCTION);
    }

}
