package br.com.codenation.logstackapi.controller;

import br.com.codenation.logstackapi.builders.TriggerBuilder;
import br.com.codenation.logstackapi.builders.TriggerRequestDTOBuilder;
import br.com.codenation.logstackapi.builders.UserBuilder;
import br.com.codenation.logstackapi.dto.request.TriggerRequestDTO;
import br.com.codenation.logstackapi.mappers.TriggerMapper;
import br.com.codenation.logstackapi.model.entity.Trigger;
import br.com.codenation.logstackapi.model.entity.User;
import br.com.codenation.logstackapi.repository.TriggerRepository;
import br.com.codenation.logstackapi.service.SecurityService;
import br.com.codenation.logstackapi.service.TriggerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.*;

import static br.com.codenation.logstackapi.util.TestUtil.convertObjectToJsonBytes;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TriggerControllerTest {

    private static String URI = "/api/v1/triggers";

    @MockBean
    private TriggerRepository triggerRepository;

    @MockBean
    private SecurityService securityService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TriggerService service;

    @Autowired
    private TriggerMapper mapper;

    @Value("${security.oauth2.client.client-id}")
    private String client;

    @Value("${security.oauth2.client.client-secret}")
    private String secret;

    private JacksonJsonParser parser = new JacksonJsonParser();
    private String token = "";

    @Before
    public void beforeTests() throws Exception {
        token = generateToken();
    }

    @Test
    public void dadoTriggerInexistente_quandoBuscoPorId_entaoRetornaNaoEncontrado() throws Exception {

        UUID id = UUID.randomUUID();

        mvc.perform(MockMvcRequestBuilders.get(URI + id.toString())
                .header("Authorization", token))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void dadoNenhumaTriggerExistente_quandoBuscarTodasTrigger_deveRetornarNenhumaTrigger() throws Exception {

        User user = UserBuilder.codenation().build();
        Mockito.when(securityService.getUserAuthenticated()).thenReturn(user);
        Mockito.when(triggerRepository.findByCreatedById(user.getId())).thenReturn(new ArrayList<>());

        mvc.perform(get(URI)
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void dadoTriggerInexistente_quandoArquivarPorId_entaoRetornaNaoEncontrado() throws Exception {

        UUID id = UUID.randomUUID();

        mvc.perform(MockMvcRequestBuilders.post(URI + id.toString() + "/archive")
                .header("Authorization", token))
                .andExpect(status().isNotFound());
    }

    @Test
    public void dadoTriggerInexistente_quandoDesarquivarPorId_entaoRetornaNaoEncontrado() throws Exception {

        UUID id = UUID.randomUUID();

        mvc.perform(MockMvcRequestBuilders.delete(URI + id.toString() + "/archive")
                .header("Authorization", token))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void dadoTriggerExistente_quandoBuscarTodasTriggerPorId_deveRetornarTrigger() throws Exception {
        Trigger t4 = TriggerBuilder.gatilho4().ativo().build();
        List<Trigger> list = Arrays.asList(t4);

        User user = UserBuilder.codenation().build();

        Mockito.when(securityService.getUserAuthenticated()).thenReturn(user);
        Mockito.when(triggerRepository.findByCreatedById(user.getId())).thenReturn(list);

        ResultActions perform = mvc.perform(get(URI)
                .header("Authorization", token)
                .content(convertObjectToJsonBytes(list))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        perform.andExpect(jsonPath("$[0].name", is("Trigger 4 de demonstração")));
        perform.andExpect(jsonPath("$[0].message", is("Message 4")));
    }

    @Test
    public void dadoTriggerInativo_quandoAtivaTrigger_deveRetornarTriggerAtiva() throws Exception {
        Trigger trigger = TriggerBuilder.gatilho1().inativo().desarquivado().build();
        Mockito.when(triggerRepository.save(trigger)).thenReturn(trigger);
        Mockito.when(triggerRepository.findById(trigger.getId())).thenReturn(java.util.Optional.of(trigger));

        ResultActions perform = mvc.perform(post(URI + "/" + trigger.getId() + "/active")
                .header("Authorization", token))
                .andExpect(status().isOk());

        perform.andExpect(jsonPath("$.name", is("Trigger 1 de demonstração")));
        perform.andExpect(jsonPath("$.isActive", is(true)));
    }

    @Test
    @Transactional
    public void dadoTriggerAtivo_quandoInativaTrigger_deveRetornarTriggerInativa() throws Exception {
        Trigger trigger = TriggerBuilder.gatilho1().ativo().arquivado().build();
        Mockito.when(triggerRepository.save(trigger)).thenReturn(trigger);
        Mockito.when(triggerRepository.findById(trigger.getId())).thenReturn(java.util.Optional.of(trigger));

        ResultActions perform = mvc.perform(delete(URI + "/" + trigger.getId() + "/active")
                .header("Authorization", token))
                .andExpect(status().isOk());

        perform.andExpect(jsonPath("$.name", is("Trigger 1 de demonstração")));
        perform.andExpect(jsonPath("$.isActive", is(false)));

    }

    @Test
    @Transactional
    public void dadoTriggerArquivado_quandoDesarquivarTrigger_deveRetornarTriggerDesarquivada() throws Exception {
        Trigger trigger = TriggerBuilder.gatilho1().ativo().arquivado().build();
        Mockito.when(triggerRepository.save(trigger)).thenReturn(trigger);
        Mockito.when(triggerRepository.findById(trigger.getId())).thenReturn(java.util.Optional.of(trigger));

        ResultActions perform = mvc.perform(delete(URI + "/" + trigger.getId() + "/archive")
                .header("Authorization", token))
                .andExpect(status().isOk());

        perform.andExpect(jsonPath("$.name", is("Trigger 1 de demonstração")));

    }

    @Test
    public void dadoTriggerNaoExistente_quandoArquivar_deveRetornarAlertaNaoEncontrado() throws Exception {
        UUID id = UUID.randomUUID();
        Mockito.when(triggerRepository.findById(id)).thenReturn(Optional.empty());

        ResultActions perform = mvc.perform(post(URI + "/" + id + "/archive")
                .header("Authorization", token));

        perform.andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void dadoTriggerDesarquivada_quandoArquivarTrigger_deveRetornarTriggerArquivada() throws Exception {
        Trigger trigger = TriggerBuilder.gatilho1().ativo().arquivado().build();
        Mockito.when(triggerRepository.findById(trigger.getId())).thenReturn(java.util.Optional.of(trigger));
        Mockito.when(triggerRepository.save(trigger)).thenReturn(trigger);


        ResultActions perform = mvc.perform(post(URI + "/" + trigger.getId() + "/archive")
                .header("Authorization", token))
                .andExpect(status().isOk());

        perform.andExpect(jsonPath("$.name", is("Trigger 1 de demonstração")));
    }

    @Test
    @Transactional
    public void dadoTriggerExistente_quandoAtualizarTrigger_deveRetornarTriggerAtualizaada() throws Exception {
        TriggerRequestDTO triggerRequest = TriggerRequestDTOBuilder.gatilho1().build();
        Trigger primeiraTrigger = mapper.map(triggerRequest);
        UUID id = UUID.randomUUID();
        primeiraTrigger.setId(id);
        Mockito.when(triggerRepository.save(primeiraTrigger)).thenReturn(primeiraTrigger);
        Mockito.when(triggerRepository.findById(id)).thenReturn(java.util.Optional.of(primeiraTrigger));
        triggerRequest.setMessage("teste");

        ResultActions perform = mvc.perform(put(URI + "/" + id.toString())
                .header("Authorization", token)
                .content(convertObjectToJsonBytes(triggerRequest))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());

        perform.andExpect(jsonPath("$.name", is("Name")));
        perform.andExpect(jsonPath("$.message", is("teste")));
    }

    @Test
    @Transactional
    public void dadoTriggerNaoExistente_quandoAtualizarTrigger_deveRetornarErro() throws Exception {
        TriggerRequestDTO triggerRequestAtualiza = TriggerRequestDTOBuilder.gatilho2().build();

        mvc.perform(put(URI + "/" + UUID.randomUUID())
                .header("Authorization", token)
                .content(convertObjectToJsonBytes(triggerRequestAtualiza))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void dadoTriggerSemAutenticacao_quandoAtualizarTrigger_deveRetornarErro() throws Exception {
        TriggerRequestDTO triggerRequestAtualiza = TriggerRequestDTOBuilder.gatilho2().build();

        mvc.perform(put(URI + "/" + UUID.randomUUID())
                .content(convertObjectToJsonBytes(triggerRequestAtualiza))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().is(401));
    }

    @Test
    public void dadoTriggerSemAutenticacao_quandoBuscarTodasTriggerPorId_deveRetornarErro() throws Exception {
        Trigger t4 = TriggerBuilder.gatilho4().ativo().build();
        List<Trigger> list = Arrays.asList(t4);

        ResultActions perform = mvc.perform(get(URI)
                .content(convertObjectToJsonBytes(list))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().is(401));
    }

    @Test
    public void dadoTriggerInativoSemAutenticacao_quandoAtivaTrigger_deveRetornarErro() throws Exception {
        Trigger trigger = TriggerBuilder.gatilho1().inativo().desarquivado().build();

        ResultActions perform = mvc.perform(post(URI + "/" + trigger.getId() + "/active"))
                .andExpect(status().is(401));
    }

    @Test
    public void dadoTriggerNaoExistente_quandoAtivar_deveRetornarAlertaNaoEncontrado() throws Exception {
        UUID id = UUID.randomUUID();
        Mockito.when(triggerRepository.findById(id)).thenReturn(Optional.empty());

        ResultActions perform = mvc.perform(post(URI + "/" + id + "/active")
                .header("Authorization", token));

        perform.andExpect(status().isNotFound());
    }

    @Test
    public void dadoTriggerAtivoSemAutenticacao_quandoInativaTrigger_deveRetornarErro() throws Exception {
        Trigger trigger = TriggerBuilder.gatilho1().ativo().arquivado().build();

        ResultActions perform = mvc.perform(delete(URI + "/" + trigger.getId() + "/active"))
                .andExpect(status().is(401));
    }

    @Test
    public void dadoTriggerNaoExistente_quandoDesativar_deveRetornarAlertaNaoEncontrado() throws Exception {
        UUID id = UUID.randomUUID();
        Mockito.when(triggerRepository.findById(id)).thenReturn(Optional.empty());

        ResultActions perform = mvc.perform(delete(URI + "/" + id + "/active")
                .header("Authorization", token));

        perform.andExpect(status().isNotFound());
    }

    @Test
    public void dadoTriggerSemAutenticacao_quandoSalvarTrigger_deveRetornarErro() throws Exception {
        TriggerRequestDTO triggerRequest = TriggerRequestDTOBuilder.gatilho1().build();
        Trigger trigger = mapper.map(triggerRequest);

        ResultActions perform = mvc.perform(post(URI)
                .content(convertObjectToJsonBytes(triggerRequest))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().is(401));
    }

    @Test
    public void cadastrarUmTrigger() throws Exception{
        Trigger trigger = TriggerBuilder.gatilho1().ativo().desarquivado().build();
        TriggerRequestDTO triggerRequestDTOBuilder = TriggerRequestDTOBuilder.gatilho1().build();

        ResultActions triggerSalvo = mvc.perform(post("/api/v1/triggers")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(triggerRequestDTOBuilder)));

        triggerSalvo.andExpect(status().isOk());


    }

    private String generateToken() throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", "admin@admin.com");
        params.add("password", "admin");

        ResultActions login = mvc.perform(
                post("/oauth/token")
                        .params(params)
                        .accept("application/json;charset=UTF-8")
                        .with(httpBasic(client, secret)))
                .andExpect(status().isOk());

        String token = parser.parseMap(login
                .andReturn()
                .getResponse()
                .getContentAsString()).get("access_token").toString();

        return String.format("Bearer %s", token);

    }
}
