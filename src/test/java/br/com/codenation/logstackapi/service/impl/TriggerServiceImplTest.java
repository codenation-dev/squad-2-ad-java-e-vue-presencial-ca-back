package br.com.codenation.logstackapi.service.impl;


import br.com.codenation.logstackapi.builders.TriggerBuilder;
import br.com.codenation.logstackapi.builders.TriggerRequestDTOBuilder;
import br.com.codenation.logstackapi.dto.request.TriggerRequestDTO;
import br.com.codenation.logstackapi.exception.ResourceNotFoundException;
import br.com.codenation.logstackapi.mappers.TriggerMapper;
import br.com.codenation.logstackapi.model.entity.Trigger;
import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import br.com.codenation.logstackapi.model.enums.LogLevel;
import br.com.codenation.logstackapi.repository.TriggerRepository;
import br.com.codenation.logstackapi.service.TriggerService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertThat;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TriggerServiceImplTest {

    @Autowired
    private TriggerMapper mapper;

    @Autowired
    private TriggerService triggerService;

    @MockBean
    private TriggerRepository repository;

    @Test
    public void dadoTriggerExistente_quandoPesquisarPorId_entaoDeveEncontrarTrigger() {
        Trigger trigger = TriggerBuilder.gatilho1().build();
        UUID triggerId = trigger.getId();
        Mockito.when(repository.findById(triggerId)).thenReturn(java.util.Optional.of(trigger));

        Trigger triggerValidation = triggerService.findById(triggerId);

        Assert.assertThat(triggerValidation, Matchers.notNullValue());
        Assert.assertThat(triggerValidation.getId(), Matchers.equalTo(triggerId));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void dadoTriggerNaoExistente_quandoPesquisarPorId_entaoNaoDeveEncontrarTrigger() {
        Trigger trigger = TriggerBuilder.gatilho1().build();
        UUID triggerId = trigger.getId();
        Mockito.when(repository.findById(UUID.randomUUID())).thenReturn(java.util.Optional.of(trigger));

        triggerService.findById(triggerId);
    }

    @Test
    public void dadoTriggerNaoArquivado_quandoAtivar_entaoDeveRetornarTriggerArquivado() {
        Trigger trigger = TriggerBuilder.gatilho1().desarquivado().build();
        Mockito.when(repository.findById(trigger.getId())).thenReturn(java.util.Optional.of(trigger));
        Mockito.when(repository.save(trigger)).thenReturn(trigger);

        Trigger triggerArquivado = triggerService.archive(trigger.getId());

        Assert.assertThat(triggerArquivado, Matchers.notNullValue());
        Assert.assertThat(triggerArquivado.getArchived(), Matchers.equalTo(Boolean.TRUE));
    }

    @Test
    public void dadoTriggerArquivado_quandoDesarquivar_entaoDeveRetornarTriggerNaoArquivado() {
        Trigger trigger = TriggerBuilder.gatilho1().arquivado().build();
        Mockito.when(repository.findById(trigger.getId())).thenReturn(java.util.Optional.of(trigger));
        Mockito.when(repository.save(trigger)).thenReturn(trigger);

        Trigger triggerArquivado = triggerService.unarchive(trigger.getId());

        Assert.assertThat(triggerArquivado, Matchers.notNullValue());
        Assert.assertThat(triggerArquivado.getArchived(), Matchers.equalTo(Boolean.FALSE));
    }

    @Test
    public void dadoTriggerAtiva_quandoDesativar_entaoDeveRetornarTriggerInativo() {
        Trigger trigger = TriggerBuilder.gatilho1().ativo().build();
        Mockito.when(repository.findById(trigger.getId())).thenReturn(java.util.Optional.of(trigger));
        Mockito.when(repository.save(trigger)).thenReturn(trigger);

        Trigger triggerArquivado = triggerService.inactive(trigger.getId());

        Assert.assertThat(triggerArquivado, Matchers.notNullValue());
        Assert.assertThat(triggerArquivado.getActive(), Matchers.equalTo(Boolean.FALSE));
    }

    @Test
    public void dadoTriggerInativa_quandoAtivar_entaoDeveRetornarTriggerAtiva() {
        Trigger trigger = TriggerBuilder.gatilho1().inativo().build();
        Mockito.when(repository.findById(trigger.getId())).thenReturn(java.util.Optional.of(trigger));
        Mockito.when(repository.save(trigger)).thenReturn(trigger);

        Trigger triggerArquivado = triggerService.active(trigger.getId());

        Assert.assertThat(triggerArquivado, Matchers.notNullValue());
        Assert.assertThat(triggerArquivado.getActive(), Matchers.equalTo(Boolean.TRUE));
    }

    @Test
    public void dadoTriggerRequestDTO_quandoSalvar_entaoDeveRetornarTriggerSalva() {
        TriggerRequestDTO dto = TriggerRequestDTOBuilder.gatilho1().build();
        Trigger trigger = mapper.map(dto);
        trigger.setArchived(false);
        Mockito.when(repository.save(trigger)).thenReturn(trigger);

        Trigger result = triggerService.save(dto);

        Assert.assertThat(result, Matchers.notNullValue());
        Assert.assertThat(result.getFilters().getAppName(), Matchers.equalTo(dto.getFilters().getAppName()));
        Assert.assertThat(result.getActive(), Matchers.equalTo(dto.getIsActive()));
        Assert.assertThat(result.getArchived(), Matchers.equalTo(Boolean.FALSE));
    }

    @Test
    public void dadoTriggersExistentes_quandoBuscarTodasTriggers_entaoDeveRetornarTodasTriggers() {
        List<Trigger> listaTrigger = new ArrayList<>();
        listaTrigger.add(TriggerBuilder.gatilho1().arquivado().build());
        listaTrigger.add(TriggerBuilder.gatilho2().arquivado().build());
        listaTrigger.add(TriggerBuilder.gatilho4().arquivado().build());
        Mockito.when(repository.findAll()).thenReturn(listaTrigger);

        List<Trigger> retornoListaTrigger = triggerService.findAll();

        assertThat(retornoListaTrigger.stream().count(), Matchers.equalTo(3L));
        assertThat(retornoListaTrigger.stream().filter(c -> c.getName().equals("Trigger 4 de demonstração")).count(), Matchers.equalTo(1L));
        assertThat(retornoListaTrigger.stream().filter(c -> c.getMessage().equals("Message 4")).findFirst().map(c -> c.getMessage()).orElse(null), Matchers.equalTo("Message 4"));
    }

    @Test
    public void dadoTriggersExistentes_quandoAtualizarPorId_entaoDeveRetornarTriggerAtualizada() {
        TriggerRequestDTO triggerRequestDTO = TriggerRequestDTOBuilder.gatilho1().build();
        Trigger trigger = mapper.map(triggerRequestDTO);
        Mockito.when(repository.save(trigger)).thenReturn(trigger);
        Mockito.when(repository.findById(trigger.getId())).thenReturn(java.util.Optional.of(trigger));

        Trigger triggerAtualizada = triggerService.update(trigger.getId(), triggerRequestDTO);

        Assert.assertThat(triggerAtualizada, Matchers.notNullValue());
        Assert.assertThat(triggerAtualizada.getFilters().getAppName(), Matchers.equalTo(triggerRequestDTO.getFilters().getAppName()));
    }

    @Test
    public void dadoTriggersExistentes_quandoPesquisaPorDadosDoLog_entaoDeveRetornarTriggersCadastradas() {

        String appName = "logstack-api";
        LogEnvironment environment = LogEnvironment.PRODUCTION;
        LogLevel level = LogLevel.ERROR;

        List<Trigger> triggers = Arrays.asList(
                TriggerBuilder.gatilho1().ativo().build(),
                TriggerBuilder.gatilho1().ativo().build(),
                TriggerBuilder.gatilho1().ativo().build(),
                TriggerBuilder.gatilho1().ativo().build(),
                TriggerBuilder.gatilho1().ativo().build());

        Mockito.when(repository.findByActiveTrueAndArchivedFalseAndFiltersAppNameAndFiltersEnvironmentAndFiltersLevel(
                appName, environment, level
        )).thenReturn(triggers);

        List<Trigger> result = triggerService.findByLog(appName, environment, level);

        Assert.assertThat(result, Matchers.notNullValue());
        Assert.assertThat(result.get(0).getFilters().getAppName(), Matchers.equalTo(appName));
        Assert.assertThat(result.get(0).getFilters().getEnvironment(), Matchers.equalTo(environment));
        Assert.assertThat(result.get(0).getFilters().getLevel(), Matchers.equalTo(level));
    }

}
