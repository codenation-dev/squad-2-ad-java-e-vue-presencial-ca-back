package br.com.codenation.logstackapi.controller;


import br.com.codenation.logstackapi.builders.CustomerBuilder;
import br.com.codenation.logstackapi.builders.LogBuilder;
import br.com.codenation.logstackapi.builders.LogRequestDTOBuilder;
import br.com.codenation.logstackapi.builders.UserBuilder;
import br.com.codenation.logstackapi.dto.request.LogRequestDTO;
import br.com.codenation.logstackapi.mappers.LogMapper;
import br.com.codenation.logstackapi.model.entity.Customer;
import br.com.codenation.logstackapi.model.entity.Log;
import br.com.codenation.logstackapi.model.entity.User;
import br.com.codenation.logstackapi.repository.CustomerRepository;
import br.com.codenation.logstackapi.repository.LogRepository;
import br.com.codenation.logstackapi.service.LogService;
import br.com.codenation.logstackapi.service.SecurityService;
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
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static br.com.codenation.logstackapi.util.TestUtil.convertObjectToJsonBytes;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class LogControllerTest {

    private static String URI = "/api/v1/logs";

    @MockBean
    private SecurityService securityService;

    @MockBean
    private LogRepository logRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private LogService logService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private LogMapper mapper;

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
    public void dadoLog_quandoSalvarLog_EntaoDeveRetornarLog() throws Exception {
        LogRequestDTO logRequestDTO = LogRequestDTOBuilder.umLog().build();
        Log log = mapper.map(logRequestDTO);
        Customer customer = CustomerBuilder.codenation().build();
        log.setCustomer(customer);
        log.setArchived(false);
        log.setCheckAlert(false);

        Mockito.when(logRepository.save(log)).thenReturn(log);
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        Mockito.when(customerRepository.findByApiKey(customer.getApiKey())).thenReturn(Optional.of(customer));

        ResultActions logSalvo = mvc.perform(post("/api/v1/logs")
                .header("Authorization", token)
                .param("apiKey", String.valueOf(customer.getApiKey()))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(logRequestDTO)))
                .andExpect(status().isOk());

        logSalvo.andExpect(jsonPath("$.title", is(log.getTitle())));
        logSalvo.andExpect(jsonPath("$.application.name", is(log.getApplication().getName())));
    }

    @Test
    public void dadoLogExistente_quandoBuscarPorId_EntaoDeveRetornarLog() throws Exception {
        Log log = LogBuilder.umLog().emDesenvolvimento().comLevelDebug().arquivado().build();

        Mockito.when(logRepository.findById(log.getId())).thenReturn(Optional.of(log));

        ResultActions perform = mvc.perform(get(URI + "/" + log.getId())
                .header("Authorization", token))
                .andExpect(status().isOk());

        perform.andExpect(jsonPath("$.id", is(log.getId().toString())));
        perform.andExpect(jsonPath("$.title", is(log.getTitle())));
    }

    @Test
    public void dadoLogExistente_quandoBuscarPorIdSemAutenticacao_EntaoDeveErro() throws Exception {
        Log log = LogBuilder.umLog().emDesenvolvimento().comLevelDebug().arquivado().build();

        ResultActions perform = mvc.perform(get(URI + "/" + log.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().is(401));
    }

    @Test
    public void dadoLogDesarquivado_quandoArquivar_EntaoDeveRetornarLogArquivado() throws Exception {
        Log log = LogBuilder.umLog().emDesenvolvimento().comLevelDebug().naoArquivado().build();

        Mockito.when(logRepository.findById(log.getId())).thenReturn(Optional.of(log));
        Mockito.when(logRepository.save(log)).thenReturn(log);

        ResultActions perform = mvc.perform(post(URI + "/" + log.getId() + "/archive")
                .header("Authorization", token))
                .andExpect(status().isOk());

        perform.andExpect(jsonPath("$.id", is(log.getId().toString())));
    }

    @Test
    public void dadoLogDesarquivado_quandoArquivarSemAutenticacao_EntaoDeveRetornarErro() throws Exception {
        Log log = LogBuilder.umLog().emDesenvolvimento().comLevelDebug().naoArquivado().build();

        Mockito.when(logRepository.findById(log.getId())).thenReturn(Optional.of(log));
        Mockito.when(logRepository.save(log)).thenReturn(log);

        ResultActions perform = mvc.perform(put(URI + "/" + log.getId() + "/archive"))
                .andExpect(status().is(401));
    }

    @Test
    public void dadoLogArquivado_quandoDesarquivar_EntaoDeveRetornarLogDesarquivado() throws Exception {
        Log log = LogBuilder.umLog().emDesenvolvimento().comLevelDebug().naoArquivado().build();

        Mockito.when(logRepository.findById(log.getId())).thenReturn(Optional.of(log));
        Mockito.when(logRepository.save(log)).thenReturn(log);

        ResultActions perform = mvc.perform(delete(URI + "/" + log.getId() + "/archive")
                .header("Authorization", token))
                .andExpect(status().isOk());

        perform.andExpect(jsonPath("$.id", is(log.getId().toString())));
    }

    @Test
    public void dadoLogArquivado_quandoDesarquivarSemAutenticacao_EntaoDeveRetornarErro() throws Exception {
        Log log = LogBuilder.umLog().emDesenvolvimento().comLevelDebug().naoArquivado().build();

        ResultActions perform = mvc.perform(delete(URI + "/" + log.getId() + "/archive"))
                .andExpect(status().is(401));
    }

    @Test
    public void dadoListaDeLogs_quandoBuscarTodosOsLogs_EntaoDeveRetornarListaDeLogs() throws Exception {
        Log umLog = LogBuilder.umLog().build();
        Log doisLog = LogBuilder.umLog().naoArquivado().comLevelError().build();
        List<Log> listaLog = Arrays.asList(umLog, doisLog);
        User user = UserBuilder.codenation().build();
        Page<Log> page = new PageImpl<>(listaLog);

        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "detail.timestamp"));
        Mockito.when(securityService.getUserAuthenticated()).thenReturn(user);
        Mockito.when(logRepository.find(null
                , null
                , null
                ,null
                ,null
                ,null
                , null
                , LocalDateTime.of(LocalDate.parse("2019-09-01"), LocalTime.of(0, 0, 0))
                , LocalDateTime.of(LocalDate.parse("2019-09-30"), LocalTime.of(23, 59, 59))
                ,user.getId()
                , pageRequest)).thenReturn(page);

        ResultActions pageResponse = mvc.perform(get(URI)
                .header("Authorization", token))
                .andExpect(status().isOk());

        pageResponse.andExpect(jsonPath("$.content[0].id", is(umLog.getId().toString())));
        pageResponse.andExpect(jsonPath("$.content[1].id", is(doisLog.getId().toString())));
    }

    @Test
    public void dadoListaDeLogs_quandoBuscarTodosOsLogsSemAutenticacao_EntaoDeveRetornarErro() throws Exception {
        ResultActions pageResponse = mvc.perform(get(URI))
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
