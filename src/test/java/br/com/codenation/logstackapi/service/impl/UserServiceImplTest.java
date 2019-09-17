package br.com.codenation.logstackapi.service.impl;

import br.com.codenation.logstackapi.LogStackApplication;
import br.com.codenation.logstackapi.builders.UserBuilder;
import br.com.codenation.logstackapi.model.entity.User;
import br.com.codenation.logstackapi.repository.UserRepository;
import br.com.codenation.logstackapi.service.UserService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThat;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository repository;

    @Test
    public void dadoUsuarioExistente_quandoPesquisarPorEmail_entaoDeveEncontrarUsuario() {
        User user = UserBuilder.admin().build();
        Mockito.when(repository.findByEmail("admin@hotmail.com")).thenReturn(Optional.of(user));

        User userValidationEmail = userService.findByEmail("admin@hotmail.com").orElse(null);

        assertThat(userValidationEmail, Matchers.notNullValue());
        assertThat(userValidationEmail.getFullName(), Matchers.equalTo("Admin"));
        assertThat(userValidationEmail.getEmail(), Matchers.equalTo("admin@hotmail.com"));
    }

    @Test
    public void dadoUsuarioNaoExistente_quandoPesquisarPorEmail_entaoNaoDeveEncontrarUsuario(){
        User user = UserBuilder.admin().build();
        Mockito.when(repository.findByEmail("admin@hotmail.com")).thenReturn(Optional.of(user));

        User userValidationEmailNotFound = userService.findByEmail("error@hotmail.com").orElse(null);

        assertThat(userValidationEmailNotFound, Matchers.nullValue());
    }

    @Test
    public void dadoUsuariosExistentes_quandoBuscarTodosUsuarios_entaoDeveRetornarTodosUsuarios(){
        List<User> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(UserBuilder.admin().build());
        listaUsuarios.add(UserBuilder.squadnation().build());
        listaUsuarios.add(UserBuilder.codenation().build());
        Mockito.when(repository.findAll()).thenReturn(listaUsuarios);

        List<User> retornoListaUsuario = userService.findAll();

        assertThat(retornoListaUsuario.stream().count(), Matchers.equalTo(3L));
        assertThat(retornoListaUsuario.stream().filter(c -> c.getEmail().equals("codenation@hotmail.com")).count(), Matchers.equalTo(1L));
        assertThat(retornoListaUsuario.stream().filter(c -> c.getEmail().equals("error@hotmail.com")).count(), Matchers.equalTo(0L));
        assertThat(retornoListaUsuario.stream().filter(c -> c.getEmail().equals("codenation@hotmail.com")).findFirst().map(c -> c.getEmail()).orElse(null), Matchers.equalTo("codenation@hotmail.com"));
    }
}
