package br.com.codenation.logstackapi.mappers;

import br.com.codenation.logstackapi.dto.request.TriggerRequestDTO;
import br.com.codenation.logstackapi.dto.response.TriggerResponseDTO;
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
            @Mapping(source = "message", target = "message"),
            @Mapping(source = "createdBy", target = "createdBy"),
            @Mapping(source = "active", target = "isActive"),
            @Mapping(source = "filters", target = "filters")
    })
    TriggerResponseDTO map(Trigger trigger);

    List<TriggerResponseDTO> map(List<Trigger> trigger);

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "message", target = "message"),
            @Mapping(source = "filters", target = "filters")
    })
    Trigger map(TriggerRequestDTO dto);

}
