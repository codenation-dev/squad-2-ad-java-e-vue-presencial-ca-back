package br.com.codenation.logstackapi.mappers;

import br.com.codenation.logstackapi.dto.TriggerCreateDTO;
import br.com.codenation.logstackapi.dto.TriggerDTO;
import br.com.codenation.logstackapi.model.entity.Trigger;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TriggerMapper {

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "appName", target = "appName"),
            @Mapping(source = "environment", target = "environment"),
            @Mapping(source = "level", target = "level"),
    })
    Trigger map(TriggerCreateDTO dto);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "appName", target = "appName"),
            @Mapping(source = "environment", target = "environment"),
            @Mapping(source = "level", target = "level"),
    })
    TriggerDTO map(Trigger dto);

    List<TriggerDTO> map(List<Trigger> triggers);

}
