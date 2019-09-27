package br.com.codenation.logstackapi.controller;

import br.com.codenation.logstackapi.builders.UserResquestBuilder;
import br.com.codenation.logstackapi.dto.request.UserRequestDTO;
import br.com.codenation.logstackapi.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static br.com.codenation.logstackapi.util.TestUtil.convertObjectToJsonBytes;
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
public class OAuthControllerTest {

    private static String URI = "/oauth";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserServiceImpl userService;

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
    @Transactional
    public void dadoNovoUsuario_quandoRegistrar_entaoDeveRetornarSucesso() throws Exception {

        UserRequestDTO user = UserResquestBuilder.usuarioComum().build();

        ResultActions perform = mvc.perform(post(URI + "/signup")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(user)))
                .andExpect(status().isCreated());

        perform.andExpect(jsonPath("$.email", is(user.getEmail())));
        perform.andExpect(jsonPath("$.fullName", is(user.getFullName())));

    }

    @Test
    @Transactional
    public void dadoUsuarioComEmailExistente_quandoRegistrar_entaoDeveRetornarErro() throws Exception {

        UserRequestDTO user = UserResquestBuilder.usuarioAdmin().build();

        ResultActions perform = mvc.perform(post(URI + "/signup")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void dadoUsuarioLogado_quandoPesquisarDadosDeUsuarioLogado_entaoDeveRetornarUsuario() throws Exception {

        ResultActions perform = mvc.perform(get(URI + "/self")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());

        perform.andExpect(jsonPath("$.email", is("admin@admin.com")));
    }

    @Test
    public void dadoUsuarioNaoLogado_quandoPesquisarDadosDeUsuarioLogado_entaoDeveErro() throws Exception {

        ResultActions perform = mvc.perform(get(URI + "/self")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
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
