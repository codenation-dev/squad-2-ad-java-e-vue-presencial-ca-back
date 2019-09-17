package br.com.codenation.logstackapi.controller;

import br.com.codenation.logstackapi.builders.UserResquestBuilder;
import br.com.codenation.logstackapi.dto.request.UserRequestDTO;
import br.com.codenation.logstackapi.service.impl.UserServiceImpl;
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
import org.springframework.transaction.annotation.Transactional;

import static br.com.codenation.logstackapi.util.TestUtil.convertObjectToJsonBytes;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserControllerTest {

    private static String URI = "/api/v1/users";
    @Autowired
    private MockMvc mvc;
    @Autowired
    private UserServiceImpl userService;

    @Test
    @Transactional
    public void dadoNovoUsuario_quandoRegistrar_entaoDeveRetornarSucesso() throws Exception {

        UserRequestDTO user = UserResquestBuilder.usuarioAdmin().build();

        ResultActions perform = mvc.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(user)))
                .andExpect(status().isCreated());

        perform.andExpect(jsonPath("$.email", is("admin@example.com")));
        perform.andExpect(jsonPath("$.fullName", is("Usuário Administrador")));

    }

    @Test
    @Transactional
    public void dadoUsuariosDoisExistentes_quandoBuscarTodos_entaoDeveRetornarDoisUsuarios() throws Exception {

        this.userService.save(UserResquestBuilder.usuarioAdmin().build());
        this.userService.save(UserResquestBuilder.usuarioComum().build());

        ResultActions perform = mvc.perform(get(URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        perform.andExpect(jsonPath("$[0].email", is("admin@example.com")));
        perform.andExpect(jsonPath("$[0].fullName", is("Usuário Administrador")));

        perform.andExpect(jsonPath("$[1].email", is("comum@example.com")));
        perform.andExpect(jsonPath("$[1].fullName", is("Usuário Comum")));

    }

    @Test
    @Transactional
    public void dadoNenhumUsuarioExistente_quandoBuscarTodosUsuarios_deveRetornarNenhumUsuario() throws Exception {
        mvc.perform(get(URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @Transactional
    public void dadoUsuarioComEmailExistente_quandoRegistrar_entaoDeveRetornarErro() throws Exception {

        this.userService.save(UserResquestBuilder.usuarioAdmin().build());

        UserRequestDTO user = UserResquestBuilder.usuarioAdmin().build();

        ResultActions perform = mvc.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(user)))
                .andExpect(status().isBadRequest());

    }


}
