package br.com.codenation.logstackapi.controller;

import br.com.codenation.logstackapi.builders.TriggerBuilder;
import br.com.codenation.logstackapi.dto.request.TriggerRequestDTO;
import br.com.codenation.logstackapi.model.entity.Trigger;
import br.com.codenation.logstackapi.repository.TriggerRepository;
import br.com.codenation.logstackapi.service.TriggerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static br.com.codenation.logstackapi.TestUtil.convertObjectToJsonBytes;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TriggerControllerTest {

    private static String URI = "/api/v1/triggers";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TriggerService service;

    @Autowired
    private TriggerRepository repository;

    @Test
    public void dadoTriggerInexistente_quandoBuscoPorId_entaoRetornaNaoEncontrado() throws Exception {

        UUID id = UUID.randomUUID();

        mvc.perform(MockMvcRequestBuilders.get(URI + id.toString()))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void dadoNenhumaTriggerExistente_quandoBuscarTodasTrigger_deveRetornarNenhumaTrigger() throws Exception {

        mvc.perform(get(URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void dadoTriggerInexistente_quandoArquivarPorId_entaoRetornaNaoEncontrado() throws Exception {

        UUID id = UUID.randomUUID();

        mvc.perform(MockMvcRequestBuilders.put(URI + id.toString() + "/archive"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void dadoTriggerInexistente_quandoDesarquivarPorId_entaoRetornaNaoEncontrado() throws Exception {

        UUID id = UUID.randomUUID();

        mvc.perform(MockMvcRequestBuilders.delete(URI + id.toString() + "/archive"))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void dadoTriggerExistente_quandoBuscarTodasTriggerPorId_deveRetornarTrigger() throws Exception {
        Trigger trigger = TriggerBuilder.gatilho4().ativo().build();

        this.repository.save(trigger);

        ResultActions perform = mvc.perform(get(URI)
                .content(convertObjectToJsonBytes(trigger))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        perform.andExpect(jsonPath("$[0].name", is("Trigger 4 de demonstração")));
        perform.andExpect(jsonPath("$[0].message", is("Message 4")));
    }
}
