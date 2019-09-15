package br.com.codenation.logstackapi.mappers;

import br.com.codenation.logstackapi.dto.request.UserRequestDTO;
import br.com.codenation.logstackapi.dto.response.UserResponseDTO;
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
    UserResponseDTO map(User user);

    List<UserResponseDTO> map(List<User> user);

    @Mappings({
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "fullName", target = "fullName"),
            @Mapping(source = "password", target = "password"),
    })
    User map(UserRequestDTO dto);

}
