package br.com.codenation.logstackapi.mappers;

import br.com.codenation.logstackapi.dto.response.AlertResponseDTO;
import br.com.codenation.logstackapi.model.entity.Alert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlertMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "log", target = "log"),
            @Mapping(source = "trigger", target = "trigger"),
            @Mapping(source = "createdDate", target = "createdDate")
    })
    AlertResponseDTO map(Alert alert);

    List<AlertResponseDTO> map(List<Alert> alerts);

}
