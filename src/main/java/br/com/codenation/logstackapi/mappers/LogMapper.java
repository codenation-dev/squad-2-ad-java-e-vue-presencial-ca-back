package br.com.codenation.logstackapi.mappers;

import br.com.codenation.logstackapi.dto.request.LogRequestDTO;
import br.com.codenation.logstackapi.dto.response.LogResponseDTO;
import br.com.codenation.logstackapi.model.entity.Log;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LogMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "application.name", target = "application.name"),
            @Mapping(source = "application.environment", target = "application.environment"),
            @Mapping(source = "application.host", target = "application.host"),
            @Mapping(source = "application.ip", target = "application.ip"),
            @Mapping(source = "detail.timestamp", target = "detail.timestamp"),
            @Mapping(source = "detail.level", target = "detail.level"),
            @Mapping(source = "detail.content", target = "detail.content"),
            @Mapping(source = "createdBy", target = "createdBy"),
    })
    LogResponseDTO map(Log log);

    List<LogResponseDTO> map(List<Log> logs);

    @Mappings({
            @Mapping(source = "application.name", target = "application.name"),
            @Mapping(source = "application.environment", target = "application.environment"),
            @Mapping(source = "application.host", target = "application.host"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "level", target = "detail.level"),
            @Mapping(source = "timestamp", target = "detail.timestamp"),
            @Mapping(source = "detail", target = "detail.content"),
    })
    Log map(LogRequestDTO dto);



}
