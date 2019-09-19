package br.com.codenation.logstackapi.service.impl;

import br.com.codenation.logstackapi.LogStackApplication;
import br.com.codenation.logstackapi.builders.LogBuilder;
import br.com.codenation.logstackapi.exception.ResourceNotFoundException;
import br.com.codenation.logstackapi.model.entity.Log;
import br.com.codenation.logstackapi.repository.LogRepository;
import br.com.codenation.logstackapi.service.LogService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LogServiceImplTest {

    @Autowired
    private LogServiceImpl logService;

    @MockBean
    private LogRepository repository;

    @Test
    public void dadoLogExistente_quandoPesquisarPorId_entaoDeveEncontrarLog(){
        Log log = LogBuilder.umLog().build();
        UUID idLog = log.getId();
        Mockito.when(repository.findById(idLog)).thenReturn(java.util.Optional.of(log));

        Log logValidationId = logService.findById(idLog);

        Assert.assertThat(logValidationId, Matchers.notNullValue());
        Assert.assertThat(logValidationId.getTitle(), Matchers.equalTo("Título"));
        Assert.assertThat(logValidationId.getArchived(), Matchers.equalTo(Boolean.FALSE));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void dadoLogNaoExistente_quandoPesquisarPorId_entaoNaoDeveEncontrarLog(){
        Log log = LogBuilder.umLog().build();
        Mockito.when(repository.findById(log.getId())).thenReturn(java.util.Optional.of(log));

        logService.findById(UUID.randomUUID());
    }


    @Test
    public void dadoLogNaoArquivado_quandoPesquisarPorId_entaoDeveRetornarLogArquivado(){
        Log log = LogBuilder.umLog().build();
        Mockito.when(repository.findById(log.getId())).thenReturn(java.util.Optional.of(log));
        Mockito.when(repository.save(log)).thenReturn(log);

        Log logArquivado = logService.archive(log.getId());

        Assert.assertThat(logArquivado, Matchers.notNullValue());
        Assert.assertThat(logArquivado.getArchived(), Matchers.equalTo(Boolean.TRUE));
    }

    @Test
    public void dadoLogArquivado_quandoPesquisarPorId_entaoDeveRetornarLogDesarquivado(){
        Log log = LogBuilder.umLog().arquivado().build();
        Mockito.when(repository.findById(log.getId())).thenReturn(java.util.Optional.of(log));
        Mockito.when(repository.save(log)).thenReturn(log);

        Log logArquivado = logService.unarchive(log.getId());

        Assert.assertThat(logArquivado, Matchers.notNullValue());
        Assert.assertThat(logArquivado.getArchived(), Matchers.equalTo(Boolean.FALSE));
    }
}
