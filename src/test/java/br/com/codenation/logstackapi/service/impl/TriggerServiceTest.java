package br.com.codenation.logstackapi.service.impl;


import br.com.codenation.logstackapi.builders.LogBuilder;
import br.com.codenation.logstackapi.builders.TriggerBuilder;
import br.com.codenation.logstackapi.builders.TriggerRequestDTOBuilder;
import br.com.codenation.logstackapi.builders.UserBuilder;
import br.com.codenation.logstackapi.dto.request.TriggerRequestDTO;
import br.com.codenation.logstackapi.exception.ResourceNotFoundException;
import br.com.codenation.logstackapi.mappers.TriggerMapper;
import br.com.codenation.logstackapi.model.entity.Log;
import br.com.codenation.logstackapi.model.entity.Trigger;
import br.com.codenation.logstackapi.model.entity.TriggerSearch;
import br.com.codenation.logstackapi.model.entity.User;
import br.com.codenation.logstackapi.repository.TriggerRepository;
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
public class TriggerServiceTest {

    @Autowired
    private TriggerMapper mapper;

    @Autowired
    private TriggerService triggerService;

    @MockBean
    private TriggerRepository triggerRepository;

    @MockBean
    private SecurityService securityService;

    @Test
    public void dadoTriggerExistente_quandoPesquisarPorId_entaoDeveEncontrarTrigger() {
        Trigger trigger = TriggerBuilder.gatilho1().build();
        UUID triggerId = trigger.getId();
        Mockito.when(triggerRepository.findById(triggerId)).thenReturn(java.util.Optional.of(trigger));

        Trigger triggerValidation = triggerService.findById(triggerId);

        Assert.assertThat(triggerValidation, Matchers.notNullValue());
        Assert.assertThat(triggerValidation.getId(), Matchers.equalTo(triggerId));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void dadoTriggerNaoExistente_quandoPesquisarPorId_entaoNaoDeveEncontrarTrigger() {
        Trigger trigger = TriggerBuilder.gatilho1().build();
        UUID triggerId = trigger.getId();
        Mockito.when(triggerRepository.findById(UUID.randomUUID())).thenReturn(java.util.Optional.of(trigger));

        triggerService.findById(triggerId);
    }

    @Test
    public void dadoTriggerNaoArquivado_quandoAtivar_entaoDeveRetornarTriggerArquivado() {
        Trigger trigger = TriggerBuilder.gatilho1().desarquivado().build();
        Mockito.when(triggerRepository.findById(trigger.getId())).thenReturn(java.util.Optional.of(trigger));
        Mockito.when(triggerRepository.save(trigger)).thenReturn(trigger);

        Trigger triggerArquivado = triggerService.archive(trigger.getId());

        Assert.assertThat(triggerArquivado, Matchers.notNullValue());
        Assert.assertThat(triggerArquivado.getArchived(), Matchers.equalTo(Boolean.TRUE));
    }

    @Test
    public void dadoTriggerArquivado_quandoDesarquivar_entaoDeveRetornarTriggerNaoArquivado() {
        Trigger trigger = TriggerBuilder.gatilho1().arquivado().build();
        Mockito.when(triggerRepository.findById(trigger.getId())).thenReturn(java.util.Optional.of(trigger));
        Mockito.when(triggerRepository.save(trigger)).thenReturn(trigger);

        Trigger triggerArquivado = triggerService.unarchive(trigger.getId());

        Assert.assertThat(triggerArquivado, Matchers.notNullValue());
        Assert.assertThat(triggerArquivado.getArchived(), Matchers.equalTo(Boolean.FALSE));
    }

    @Test
    public void dadoTriggerAtiva_quandoDesativar_entaoDeveRetornarTriggerInativo() {
        Trigger trigger = TriggerBuilder.gatilho1().ativo().build();
        Mockito.when(triggerRepository.findById(trigger.getId())).thenReturn(java.util.Optional.of(trigger));
        Mockito.when(triggerRepository.save(trigger)).thenReturn(trigger);

        Trigger triggerArquivado = triggerService.inactive(trigger.getId());

        Assert.assertThat(triggerArquivado, Matchers.notNullValue());
        Assert.assertThat(triggerArquivado.getActive(), Matchers.equalTo(Boolean.FALSE));
    }

    @Test
    public void dadoTriggerInativa_quandoAtivar_entaoDeveRetornarTriggerAtiva() {
        Trigger trigger = TriggerBuilder.gatilho1().inativo().build();
        Mockito.when(triggerRepository.findById(trigger.getId())).thenReturn(java.util.Optional.of(trigger));
        Mockito.when(triggerRepository.save(trigger)).thenReturn(trigger);

        Trigger triggerArquivado = triggerService.active(trigger.getId());

        Assert.assertThat(triggerArquivado, Matchers.notNullValue());
        Assert.assertThat(triggerArquivado.getActive(), Matchers.equalTo(Boolean.TRUE));
    }

    @Test
    public void dadoTriggerRequestDTO_quandoSalvar_entaoDeveRetornarTriggerSalva() {
        TriggerRequestDTO dto = TriggerRequestDTOBuilder.gatilho1().build();
        Trigger trigger = mapper.map(dto);
        trigger.setArchived(false);
        Mockito.when(triggerRepository.save(trigger)).thenReturn(trigger);

        Trigger result = triggerService.save(dto);

        Assert.assertThat(result, Matchers.notNullValue());
        Assert.assertThat(result.getFilters().getAppName(), Matchers.equalTo(dto.getFilters().getAppName()));
        Assert.assertThat(result.getActive(), Matchers.equalTo(dto.getIsActive()));
        Assert.assertThat(result.getArchived(), Matchers.equalTo(Boolean.FALSE));
    }

    @Test
    public void dadoTriggersExistentes_quandoBuscarTodasTriggersDoUsuarioAutentacado_entaoDeveRetornarTodasTriggers() {

        User user = UserBuilder.codenation().build();

        Trigger t1 = TriggerBuilder.gatilho2().createdBy(user).desarquivado().build();
        Trigger t2 = TriggerBuilder.gatilho3().createdBy(user).desarquivado().build();
        Trigger t3 = TriggerBuilder.gatilho4().createdBy(user).desarquivado().build();
        List<Trigger> listaTrigger = Arrays.asList(t1, t2, t3);

        Mockito.when(triggerRepository.findByCreatedById(user.getId())).thenReturn(listaTrigger);

        List<Trigger> result = triggerService.findByCreatedBy(user);

        assertThat(result.stream().count(), Matchers.equalTo(3L));
        assertThat(result.stream().filter(c -> c.getName().equals("Trigger 4 de demonstração")).count(), Matchers.equalTo(1L));
        assertThat(result.stream().filter(c -> c.getMessage().equals("Message 4")).findFirst().map(c -> c.getMessage()).orElse(null), Matchers.equalTo("Message 4"));
    }

    @Test
    public void dadoTriggersExistentes_quandoBuscarTodasTriggers_entaoDeveRetornarTodasTriggers() {
        List<Trigger> listaTrigger = new ArrayList<>();
        listaTrigger.add(TriggerBuilder.gatilho1().arquivado().build());
        listaTrigger.add(TriggerBuilder.gatilho2().arquivado().build());
        listaTrigger.add(TriggerBuilder.gatilho4().arquivado().build());
        Mockito.when(triggerRepository.findAll()).thenReturn(listaTrigger);

        List<Trigger> retornoListaTrigger = triggerService.findAll();

        assertThat(retornoListaTrigger.stream().count(), Matchers.equalTo(3L));
        assertThat(retornoListaTrigger.stream().filter(c -> c.getName().equals("Trigger 4 de demonstração")).count(), Matchers.equalTo(1L));
        assertThat(retornoListaTrigger.stream().filter(c -> c.getMessage().equals("Message 4")).findFirst().map(c -> c.getMessage()).orElse(null), Matchers.equalTo("Message 4"));
    }

    @Test
    public void dadoTriggersExistentes_quandoAtualizarPorId_entaoDeveRetornarTriggerAtualizada() {
        TriggerRequestDTO triggerRequestDTO = TriggerRequestDTOBuilder.gatilho1().build();
        Trigger trigger = mapper.map(triggerRequestDTO);
        Mockito.when(triggerRepository.save(trigger)).thenReturn(trigger);
        Mockito.when(triggerRepository.findById(trigger.getId())).thenReturn(java.util.Optional.of(trigger));

        Trigger triggerAtualizada = triggerService.update(trigger.getId(), triggerRequestDTO);

        Assert.assertThat(triggerAtualizada, Matchers.notNullValue());
        Assert.assertThat(triggerAtualizada.getFilters().getAppName(), Matchers.equalTo(triggerRequestDTO.getFilters().getAppName()));
    }

    @Test
    public void dadoTriggersExistentes_quandoPesquisaPorDadosDoLog_entaoDeveRetornarTriggersCadastradas() {

        Log log = LogBuilder.umLog().emProducao().comLevelError().build();

        TriggerSearch search = TriggerSearch.builder()
                .appName(log.getApplication().getName())
                .environment(log.getApplication().getEnvironment())
                .level(log.getDetail().getLevel())
                .createdBy(log.getCustomer().getUser()).build();

        List<Trigger> triggers = Arrays.asList(
                TriggerBuilder.gatilho1().ativo().build(),
                TriggerBuilder.gatilho1().ativo().build(),
                TriggerBuilder.gatilho1().ativo().build(),
                TriggerBuilder.gatilho1().ativo().build(),
                TriggerBuilder.gatilho1().ativo().build());

        Mockito.when(triggerRepository.findByActiveTrueAndArchivedFalseAndFiltersAppNameAndFiltersEnvironmentAndFiltersLevelAndCreatedById(
                search.getAppName(), search.getEnvironment(), search.getLevel(), search.getCreatedBy().getId())).thenReturn(triggers);

        List<Trigger> result = triggerService.findByLog(search);

        Assert.assertThat(result, Matchers.notNullValue());
        Assert.assertThat(result.size(), Matchers.equalTo(5));
        Assert.assertThat(result.get(0).getFilters().getAppName(), Matchers.equalTo(search.getAppName()));
        Assert.assertThat(result.get(0).getFilters().getEnvironment(), Matchers.equalTo(search.getEnvironment()));
        Assert.assertThat(result.get(0).getFilters().getLevel(), Matchers.equalTo(search.getLevel()));
    }

}
