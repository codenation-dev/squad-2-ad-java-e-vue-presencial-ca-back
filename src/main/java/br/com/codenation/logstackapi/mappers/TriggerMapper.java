package br.com.codenation.logstackapi.mappers;

import br.com.codenation.logstackapi.dto.TriggerCreateDTO;
import br.com.codenation.logstackapi.model.entity.Trigger;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TriggerMapper {

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "appName", target = "appName"),
            @Mapping(source = "environment", target = "environment"),
            @Mapping(source = "level", target = "level"),
    })
    Trigger map(TriggerCreateDTO dto);

}
