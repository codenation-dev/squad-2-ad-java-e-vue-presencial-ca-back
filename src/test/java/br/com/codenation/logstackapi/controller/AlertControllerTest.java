package br.com.codenation.logstackapi.controller;

import br.com.codenation.logstackapi.builders.AlertBuilder;
import br.com.codenation.logstackapi.model.entity.Alert;
import br.com.codenation.logstackapi.repository.AlertRepository;
import br.com.codenation.logstackapi.service.AlertService;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;

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
public class AlertControllerTest {

    private static String URI = "/api/v1/alerts";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AlertService service;

    @MockBean
    private AlertRepository repository;

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
    public void dadoParametrosDaPagina_quandoPesquisarAlertas_entaoDevePaginaDeAlerta() throws Exception {

        Alert a1 = AlertBuilder.umAlert().build();
        Alert a2 = AlertBuilder.doisAlert().build();

        PageRequest pageRequest = PageRequest.of(0,20);
        Page<Alert> page = new PageImpl<>(Arrays.asList(a1, a2));

        Mockito.when(repository.find(null, null, pageRequest)).thenReturn(page);

        ResultActions perform = mvc.perform(get(URI)
                .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)));

        perform.andExpect(jsonPath("$.content[0].trigger.message", is("Trigger 1")));
        perform.andExpect(jsonPath("$.content[1].log.title", is("Título")));
    }

    @Test
    public void dadoParametrosDaPagina_quandoPesquisarAlertasSemAutenticacao_entaoDeveRetornarErro() throws Exception {
        ResultActions perform = mvc.perform(get(URI))
                .andExpect(status().is(401));

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
