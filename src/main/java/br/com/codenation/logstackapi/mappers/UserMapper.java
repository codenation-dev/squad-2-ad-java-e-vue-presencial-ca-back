package br.com.codenation.logstackapi.mappers;

import br.com.codenation.logstackapi.dto.UserCreateDTO;
import br.com.codenation.logstackapi.dto.UserDTO;
import br.com.codenation.logstackapi.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "fullName", target = "fullName"),
    })
    UserDTO map(User user);

    List<UserDTO> map(List<User> user);

    @Mappings({
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "fullName", target = "fullName"),
            @Mapping(source = "password", target = "password"),
    })
    User map(UserCreateDTO dto);

}
