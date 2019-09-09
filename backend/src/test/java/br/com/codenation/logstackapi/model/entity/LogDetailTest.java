package br.com.codenation.logstackapi.model.entity;

import br.com.codenation.logstackapi.builders.LogDetailBuilder;
import br.com.codenation.logstackapi.model.enums.LogLevel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LogDetailTest {

    @Test
    public void deveCriarLogDetailComLevelError() {
        LogDetail detail = LogDetailBuilder.detalhe().comLevelError().build();
        assertEquals(detail.getLevel(), LogLevel.ERROR);
    }

    @Test
    public void deveCriarLogDetailComLevelDebug() {
        LogDetail detail = LogDetailBuilder.detalhe().comLevelDebug().build();
        assertEquals(detail.getLevel(), LogLevel.DEBUG);
    }

    @Test
    public void deveCriarLogDetailComLevelWarning() {
        LogDetail detail = LogDetailBuilder.detalhe().comLevelWarning().build();
        assertEquals(detail.getLevel(), LogLevel.WARNING);
    }

}
