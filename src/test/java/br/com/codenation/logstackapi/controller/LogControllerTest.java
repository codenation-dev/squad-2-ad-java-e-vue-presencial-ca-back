package br.com.codenation.logstackapi.controller;


import br.com.codenation.logstackapi.builders.CustomerBuilder;
import br.com.codenation.logstackapi.builders.LogBuilder;
import br.com.codenation.logstackapi.builders.LogRequestDTOBuilder;
import br.com.codenation.logstackapi.dto.request.LogRequestDTO;
import br.com.codenation.logstackapi.model.entity.Customer;
import br.com.codenation.logstackapi.model.entity.Log;
import br.com.codenation.logstackapi.repository.CustomerRepository;
import br.com.codenation.logstackapi.repository.LogRepository;
import br.com.codenation.logstackapi.service.LogService;
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

import java.util.Optional;
import java.util.UUID;

import static br.com.codenation.logstackapi.util.TestUtil.convertObjectToJsonBytes;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class LogControllerTest {

    private static String URI = "/api/v1/logs";

    @MockBean
    private LogRepository logRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private LogService logService;

    @Autowired
    private MockMvc mvc;

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
    public void dadoLogInexistente_quandoBuscoPorId_entaoRetornaNaoEncontrado() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(URI + "/8139c634-6f18-4f73-a8f2-01b74cc6d6b2")
                .header("Authorization", token))
                .andExpect(status().isNotFound());
    }

    @Test
    public void dadoLogInexistente_quandoArquivarPorId_entaoRetornaNaoEncontrado() throws Exception {

        UUID id = UUID.randomUUID();
        mvc.perform(MockMvcRequestBuilders.post(URI + "/" + id.toString() + "/archive")
                .header("Authorization", token))
                .andExpect(status().isNotFound());
    }

    @Test
    public void dadoLogInexistente_quandoDesarquivarPorId_entaoRetornaNaoEncontrado() throws Exception {

        UUID id = UUID.randomUUID();

        mvc.perform(MockMvcRequestBuilders.delete(URI + "/" + id.toString() + "/archive")
                .header("Authorization", token))
                .andExpect(status().isNotFound());
    }

    @Test
    public void cadastrarUmLogComSucesso() throws Exception {
        Log log = LogBuilder.umLog().build();
        LogRequestDTO logRequestDTO = LogRequestDTOBuilder.umLog().build();

        Customer customer = CustomerBuilder.codenation().build();
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        Mockito.when(customerRepository.findByApiKey(customer.getApiKey())).thenReturn(Optional.of(customer));

        ResultActions logSalvo = mvc.perform(post("/api/v1/logs")
                .header("Authorization", token)
                .param("apiKey", String.valueOf(customer.getApiKey()))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(logRequestDTO)));

        logSalvo.andExpect(status().isOk());
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
