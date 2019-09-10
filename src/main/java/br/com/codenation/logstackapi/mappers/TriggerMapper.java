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
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "createdBy", target = "createdBy"),
            @Mapping(source = "active", target = "isActive"),
            @Mapping(source = "filters", target = "filters"),
    })
    TriggerDTO map(Trigger trigger);

    List<TriggerDTO> map(List<Trigger> trigger);

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "filters", target = "filters"),
    })
    Trigger map(TriggerCreateDTO dto);

}
