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
            @Mapping(source = "log.id", target = "log.id"),
            @Mapping(source = "log.detail", target = "log.detail"),
            @Mapping(source = "log.title", target = "log.title"),
            @Mapping(source = "log.application", target = "log.application"),
            @Mapping(source = "log.customer.user.id", target = "log.createdBy.id"),
            @Mapping(source = "log.customer.user.email", target = "log.createdBy.email"),
            @Mapping(source = "log.customer.user.fullName", target = "log.createdBy.fullName"),
            @Mapping(source = "trigger.id", target = "trigger.id"),
            @Mapping(source = "trigger.name", target = "trigger.name"),
            @Mapping(source = "trigger.filters", target = "trigger.filters"),
            @Mapping(source = "trigger.active", target = "trigger.isActive"),
            @Mapping(source = "trigger.email", target = "trigger.email"),
            @Mapping(source = "trigger.message", target = "trigger.message"),
            @Mapping(source = "trigger.createdBy", target = "trigger.createdBy"),
            @Mapping(source = "createdDate", target = "createdDate")
    })
    AlertResponseDTO map(Alert alert);

    List<AlertResponseDTO> map(List<Alert> alerts);

}
