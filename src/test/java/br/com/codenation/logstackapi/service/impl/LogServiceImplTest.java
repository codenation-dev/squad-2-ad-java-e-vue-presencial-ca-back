package br.com.codenation.logstackapi.service.impl;

import br.com.codenation.logstackapi.builders.LogBuilder;
import br.com.codenation.logstackapi.builders.LogRequestDTOBuilder;
import br.com.codenation.logstackapi.builders.LogSearchBuilder;
import br.com.codenation.logstackapi.dto.request.LogRequestDTO;
import br.com.codenation.logstackapi.dto.request.LogSearchDTO;
import br.com.codenation.logstackapi.exception.ResourceNotFoundException;
import br.com.codenation.logstackapi.mappers.LogMapper;
import br.com.codenation.logstackapi.model.entity.Alert;
import br.com.codenation.logstackapi.model.entity.Log;
import br.com.codenation.logstackapi.repository.LogRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.junit.Assert.assertThat;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LogServiceImplTest {

    @Autowired
    private LogServiceImpl logService;

    @Autowired
    private LogMapper mapper;

    @MockBean
    private LogRepository repository;

    @Test
    public void dadoLogExistente_quandoPesquisarPorId_entaoDeveEncontrarLog() {
        Log log = LogBuilder.umLog().build();
        UUID idLog = log.getId();
        Mockito.when(repository.findById(idLog)).thenReturn(Optional.of(log));

        Log logValidationId = logService.findById(idLog);

        assertThat(logValidationId, Matchers.notNullValue());
        assertThat(logValidationId.getTitle(), Matchers.equalTo("Título"));
        assertThat(logValidationId.getArchived(), Matchers.equalTo(Boolean.FALSE));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void dadoLogNaoExistente_quandoPesquisarPorId_entaoNaoDeveEncontrarLog() {
        Log log = LogBuilder.umLog().build();
        Mockito.when(repository.findById(log.getId())).thenReturn(Optional.of(log));

        logService.findById(UUID.randomUUID());
    }

    @Test
    public void dadoLogNaoArquivado_quandoPesquisarPorId_entaoDeveRetornarLogArquivado() {
        Log log = LogBuilder.umLog().build();
        Mockito.when(repository.findById(log.getId())).thenReturn(Optional.of(log));
        Mockito.when(repository.save(log)).thenReturn(log);

        Log logArquivado = logService.archive(log.getId());

        assertThat(logArquivado, Matchers.notNullValue());
        assertThat(logArquivado.getArchived(), Matchers.equalTo(Boolean.TRUE));
    }

    @Test
    public void dadoLogArquivado_quandoPesquisarPorId_entaoDeveRetornarLogDesarquivado() {
        Log log = LogBuilder.umLog().arquivado().build();
        Mockito.when(repository.findById(log.getId())).thenReturn(Optional.of(log));
        Mockito.when(repository.save(log)).thenReturn(log);

        Log logArquivado = logService.unarchive(log.getId());

        assertThat(logArquivado, Matchers.notNullValue());
        assertThat(logArquivado.getArchived(), Matchers.equalTo(Boolean.FALSE));
    }

    @Test
    public void dadoLogNaoVerificadoAlerta_quandoPesquisarLogsNaoVerificados_entaoDeveRetornarLogNaoVerificados() {

        Integer size = 5;

        List<Log> logs = Arrays.asList(
                LogBuilder.umLog().build(),
                LogBuilder.umLog().build(),
                LogBuilder.umLog().build(),
                LogBuilder.umLog().build(),
                LogBuilder.umLog().build());

        Mockito.when(repository.findByCheckAlert(false)).thenReturn(logs);
        List<Log> result = logService.findByCheckAlertNotVerified(size);

        assertThat(result, Matchers.notNullValue());
        assertThat(result.stream().count(), Matchers.equalTo(5L));
        assertThat(result.get(0).getCheckAlert(), Matchers.equalTo(Boolean.FALSE));

    }

    @Test
    public void dadoLog_quandoSalvar_entaoDeveRetornarLogSalvo() {

        UUID id = UUID.randomUUID();
        LogRequestDTO dto = LogRequestDTOBuilder.umLog().build();

        Log log = mapper.map(dto);
        log.setArchived(false);
        log.setCheckAlert(false);
        Mockito.when(repository.save(log)).thenReturn(log);

        Log result = logService.save(dto);

        Assert.assertThat(result, Matchers.notNullValue());
        Assert.assertThat(result.getCheckAlert(), Matchers.equalTo(Boolean.FALSE));
        Assert.assertThat(result.getArchived(), Matchers.equalTo(Boolean.FALSE));
    }

    @Test
    public void dadoLogPendenteDeVerificacao_quandoVerificar_entaoDeveRetornarLogAtualizado() {

        UUID id = UUID.randomUUID();
        Log logCheckAlertTrue = LogBuilder.umLog().id(id).checkAlert(true).arquivado().build();
        Log logCheckAlertFalse = LogBuilder.umLog().id(id).checkAlert(false).arquivado().build();
        Mockito.when(repository.findById(logCheckAlertFalse.getId())).thenReturn(Optional.of(logCheckAlertFalse));
        Mockito.when(repository.save(logCheckAlertTrue)).thenReturn(logCheckAlertTrue);

        Log result = logService.checkAlert(id, true);

        assertThat(result, Matchers.notNullValue());
        assertThat(result.getCheckAlert(), Matchers.equalTo(Boolean.TRUE));
    }

    @Test
    public void dadoLogSearch_quandoPesquisarTodosOsLogs_entaoDeveRetornarLog() {
        LogSearchDTO logSearchDTO = LogSearchBuilder.umLog().build();
        List<Log> listaLogs = new ArrayList<>();
        listaLogs.add(LogBuilder.umLog().emTeste().arquivado().build());
        listaLogs.add(LogBuilder.umLog().comLevelDebug().emDesenvolvimento().build());
        Page<Log> page = new PageImpl<>(listaLogs);

        Mockito.when(logService.find(logSearchDTO, 1, 2, Sort.by(Sort.Order.desc("title")))).thenReturn(page);

        Page<Log> pageResponse = logService.find(logSearchDTO, 1, 2,Sort.by(Sort.Order.desc("title")));

        assertThat(pageResponse, Matchers.notNullValue());
        assertThat(pageResponse.getTotalElements(), Matchers.equalTo(2L));
        assertThat(pageResponse.stream().filter(c -> c.getTitle().equals("Título")).count(), Matchers.equalTo(2L));
    }
}