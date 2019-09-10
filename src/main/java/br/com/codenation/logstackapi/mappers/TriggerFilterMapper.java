package br.com.codenation.logstackapi.mappers;

import br.com.codenation.logstackapi.dto.TriggerFilterCreateDTO;
import br.com.codenation.logstackapi.dto.TriggerFilterDTO;
import br.com.codenation.logstackapi.model.entity.TriggerFilter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TriggerFilterMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "field", target = "field"),
            @Mapping(source = "value", target = "value"),
    })
    TriggerFilterDTO map(TriggerFilter triggerFilter);

    @Mappings({
            @Mapping(source = "id", target = "trigger.id"),
            @Mapping(source = "dto.field", target = "field"),
            @Mapping(source = "dto.value", target = "value"),
    })
    TriggerFilter map(UUID triggerId, TriggerFilterCreateDTO dto);

}
