package br.com.codenation.logstackapi.mapper;

import br.com.codenation.logstackapi.builders.TriggerBuilder;
import br.com.codenation.logstackapi.builders.TriggerRequestDTOBuilder;
import br.com.codenation.logstackapi.builders.UserBuilder;
import br.com.codenation.logstackapi.builders.UserResquestBuilder;
import br.com.codenation.logstackapi.dto.request.TriggerRequestDTO;
import br.com.codenation.logstackapi.dto.request.UserRequestDTO;
import br.com.codenation.logstackapi.dto.response.TriggerResponseDTO;
import br.com.codenation.logstackapi.dto.response.UserResponseDTO;
import br.com.codenation.logstackapi.mappers.TriggerMapper;
import br.com.codenation.logstackapi.mappers.UserMapper;
import br.com.codenation.logstackapi.model.entity.Trigger;
import br.com.codenation.logstackapi.model.entity.User;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void dadoUser_quandoExecutarMapper_entaoDeveRetornarUserResponse(){
         User user = UserBuilder.admin().build();

        UserResponseDTO userResponse = userMapper.map(user);

        Assert.assertThat(userResponse, Matchers.notNullValue());
        Assert.assertThat(userResponse.getId(), Matchers.equalTo(user.getId()));
        Assert.assertThat(userResponse.getEmail(), Matchers.equalTo(user.getEmail()));
    }

    @Test
    public void dadoUserRequest_quandoExecutarMapper_entaoDeveRetornarUser(){
        UserRequestDTO userRequest = UserResquestBuilder.usuarioAdmin().build();

        User user  = userMapper.map(userRequest);

        Assert.assertThat(user, Matchers.notNullValue());
        Assert.assertNotEquals(user.getEmail(), Matchers.equalTo(userRequest.getEmail()));
        Assert.assertThat(user.getFullName(), Matchers.equalTo(userRequest.getFullName()));
    }

    @Test
    public void dadoListaUser_quandoExecutarMapper_entaoDeveRetornarListaUserResponse(){
        List<User> listaUser = new ArrayList<>();
        User primeiroUser = UserBuilder.admin().build();
        User segundoUser = UserBuilder.codenation().build();
        listaUser.add(primeiroUser);
        listaUser.add(segundoUser);

        List<UserResponseDTO> userListResponse = userMapper.map(listaUser);

        Assert.assertThat(userListResponse.size(), Matchers.equalTo(2));
        Assert.assertThat(userListResponse.stream().filter(l -> l.getId().equals(primeiroUser.getId())).count(), Matchers.equalTo(1L));
    }
}
