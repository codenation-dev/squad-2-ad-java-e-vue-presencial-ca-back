package br.com.codenation.logstackapi.mapper;

import br.com.codenation.logstackapi.builders.LogBuilder;
import br.com.codenation.logstackapi.builders.LogRequestBuilder;
import br.com.codenation.logstackapi.dto.request.LogRequestDTO;
import br.com.codenation.logstackapi.dto.response.LogResponseDTO;
import br.com.codenation.logstackapi.mappers.LogMapper;
import br.com.codenation.logstackapi.model.entity.Log;
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
public class LogMapperTest {

    @Autowired
    LogMapper logMapper;

    @Test
    public void dadoLog_quandoExecutarMapper_entaoDeveRetornarLogResponse(){
        Log log = LogBuilder.umLog().arquivado().comLevelDebug().emDesenvolvimento().build();

        LogResponseDTO logResponse = logMapper.map(log);

        Assert.assertThat(log, Matchers.notNullValue());
        Assert.assertThat(logResponse.getId(), Matchers.equalTo(log.getId()));
        Assert.assertThat(logResponse.getTitle(), Matchers.equalTo(log.getTitle()));
    }

    @Test
    public void dadoLogRequest_quandoExecutarMapper_entaoDeveRetornarLog(){
        LogRequestDTO logRequest = LogRequestBuilder.umLog().build();

        Log log = logMapper.map(logRequest);

        Assert.assertThat(log, Matchers.notNullValue());
        Assert.assertNotEquals(log.getDetail().getContent(), Matchers.equalTo(logRequest.getContent()));
        Assert.assertThat(log.getTitle(), Matchers.equalTo(logRequest.getTitle()));
    }

    @Test
    public void dadoLogListaLogs_quandoExecutarMapper_entaoDeveRetornarListaLogResponse(){
        List<Log> listaLog = new ArrayList<>();
        Log primeiroLog = LogBuilder.umLog().arquivado().comLevelDebug().emDesenvolvimento().build();
        Log segundoLog = LogBuilder.umLog().naoArquivado().comLevelWarning().emProducao().build();
        listaLog.add(primeiroLog);
        listaLog.add(segundoLog);

        List<LogResponseDTO> logResponse = logMapper.map(listaLog);
        Assert.assertThat(logResponse.size(), Matchers.equalTo(2));
        Assert.assertThat(logResponse.stream().filter(l -> l.getId().equals(primeiroLog.getId())).count(), Matchers.equalTo(1L));
    }
}
