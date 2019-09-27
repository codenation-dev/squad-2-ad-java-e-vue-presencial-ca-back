package br.com.codenation.logstackapi.mappers;

import br.com.codenation.logstackapi.dto.response.CustomerResponseDTO;
import br.com.codenation.logstackapi.model.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "apiKey", target = "apiKey"),
            @Mapping(source = "user", target = "user"),
            @Mapping(source = "createdDate", target = "createdDate"),
            @Mapping(source = "updatedDate", target = "updatedDate")
    })
    CustomerResponseDTO map(Customer customer);

}
