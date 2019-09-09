package br.com.codenation.logstackapi.mappers;

import br.com.codenation.logstackapi.dto.AlertCreateDTO;
import br.com.codenation.logstackapi.model.entity.Alert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AlertMapper {

    @Mappings({
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "appName", target = "appName"),
            @Mapping(source = "environment", target = "environment"),
            @Mapping(source = "level", target = "level"),
    })
    Alert map(AlertCreateDTO dto);

}
