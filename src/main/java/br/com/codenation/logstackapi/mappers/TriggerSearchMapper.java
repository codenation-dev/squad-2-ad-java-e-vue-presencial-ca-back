package br.com.codenation.logstackapi.mappers;

import br.com.codenation.logstackapi.model.entity.Log;
import br.com.codenation.logstackapi.model.entity.TriggerSearch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TriggerSearchMapper {

    @Mappings({
            @Mapping(source = "application.name", target = "appName"),
            @Mapping(source = "application.environment", target = "environment"),
            @Mapping(source = "detail.level", target = "level"),
            @Mapping(source = "customer.user", target = "createdBy")
    })
    TriggerSearch map(Log log);

}
