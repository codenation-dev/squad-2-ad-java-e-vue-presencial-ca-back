package br.com.codenation.logstackapi.builders;

import br.com.codenation.logstackapi.model.entity.Log;
import br.com.codenation.logstackapi.model.entity.LogApplication;
import br.com.codenation.logstackapi.model.entity.LogDetail;

import java.util.UUID;

public class LogBuilder {

    private Log log;

    private LogBuilder() {
    }

    public static LogBuilder umLog() {
        LogBuilder builder = new LogBuilder();
        builder.log = Log.builder()
                .id(UUID.randomUUID())
                .title("Título")
                .archived(false)
                .checkAlert(false)
                .customer(CustomerBuilder.codenation().build())
                .application(LogApplicationBuilder.umaAplicacao().build())
                .build();
        return builder;
    }

    public LogBuilder id(UUID id) {
        log.setId(id);
        return this;
    }

    public LogBuilder checkAlert(Boolean checkAlert) {
        log.setCheckAlert(checkAlert);
        return this;
    }

    public LogBuilder arquivado() {
        log.setArchived(true);
        return this;
    }

    public LogBuilder naoArquivado() {
        log.setArchived(false);
        return this;
    }

    public LogBuilder comLevelError() {
        LogDetail detail = LogDetailBuilder.detalhe().comLevelError().build();
        log.setDetail(detail);
        return this;
    }

    public LogBuilder comLevelDebug() {
        LogDetail detail = LogDetailBuilder.detalhe().comLevelDebug().build();
        log.setDetail(detail);
        return this;
    }

    public LogBuilder comLevelWarning() {
        LogDetail detail = LogDetailBuilder.detalhe().comLevelWarning().build();
        log.setDetail(detail);
        return this;
    }

    public LogBuilder emDesenvolvimento() {
        LogApplication application = LogApplicationBuilder.umaAplicacao().emDesenvolvimento().build();
        log.setApplication(application);
        return this;
    }

    public LogBuilder emTeste() {
        LogApplication application = LogApplicationBuilder.umaAplicacao().emTeste().build();
        log.setApplication(application);
        return this;
    }

    public LogBuilder emProducao() {
        LogApplication application = LogApplicationBuilder.umaAplicacao().emProducao().build();
        log.setApplication(application);
        return this;
    }

    public Log build() {
        return log;
    }

}
