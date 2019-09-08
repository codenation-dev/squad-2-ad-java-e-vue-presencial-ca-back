package br.com.codenation.logstackapi.model.entity;

import br.com.codenation.logstackapi.builders.LogBuilder;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class LogTest {

    @Test
    public void deveCriarLog() {
        Log log = LogBuilder.umLog().naoArquivado().emDesenvolvimento().comLevelError().build();

        assertThat(log.getId(), Matchers.notNullValue());
        assertEquals(log.getTitle(), "Título");
        assertFalse(log.getArchived());

        assertEquals(log.getApplication().getHost(), "localhost");
        assertEquals(log.getApplication().getIp(), "192.0.0.1");
        assertEquals(log.getApplication().getName(), "logstack-api");

        assertEquals(log.getDetail().getContent(), "Detalhes do log");
        assertEquals(log.getDetail().getTimestamp(), LocalDateTime.of(2019, 8, 10, 10, 55, 32, 300));

    }

    @Test
    public void deveCriarLogNaoArquivado() {
        Log log = LogBuilder.umLog().comLevelError().naoArquivado().build();
        assertFalse(log.getArchived());
    }

    @Test
    public void deveCriarLogArquivado() {
        Log log = LogBuilder.umLog().comLevelError().arquivado().build();
        assertTrue(log.getArchived());
    }

}