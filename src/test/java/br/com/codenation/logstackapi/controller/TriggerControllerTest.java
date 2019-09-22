package br.com.codenation.logstackapi.controller;

import br.com.codenation.logstackapi.builders.TriggerBuilder;
import br.com.codenation.logstackapi.dto.response.TriggerResponseDTO;
import br.com.codenation.logstackapi.mappers.TriggerMapper;
import br.com.codenation.logstackapi.model.entity.Trigger;
import br.com.codenation.logstackapi.repository.TriggerRepository;
import br.com.codenation.logstackapi.service.TriggerService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static br.com.codenation.logstackapi.util.TestUtil.convertObjectToJsonBytes;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TriggerControllerTest {

    private static String URI = "/api/v1/triggers";

    @MockBean
    private TriggerRepository repository;

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

    private ObjectMapper objectMapper = new ObjectMapper();
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

        mvc.perform(get(URI)
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void dadoTriggerInexistente_quandoArquivarPorId_entaoRetornaNaoEncontrado() throws Exception {

        UUID id = UUID.randomUUID();

        mvc.perform(MockMvcRequestBuilders.put(URI + id.toString() + "/archive")
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
        Mockito.when(repository.findAll()).thenReturn(list);

        List<TriggerResponseDTO> response = new ArrayList<>();
        response.add(mapper.map(t4));

        ResultActions perform = mvc.perform(get(URI)
                .header("Authorization", token)
                .content(convertObjectToJsonBytes(response))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        perform.andExpect(jsonPath("$[0].name", is("Trigger 4 de demonstração")));
        perform.andExpect(jsonPath("$[0].message", is("Message 4")));
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
