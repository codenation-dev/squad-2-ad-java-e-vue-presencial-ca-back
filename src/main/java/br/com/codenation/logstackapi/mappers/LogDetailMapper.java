package br.com.codenation.logstackapi.mappers;

import br.com.codenation.logstackapi.dto.response.LogDetailResponseDTO;
import br.com.codenation.logstackapi.model.entity.Log;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LogDetailMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "application.name", target = "application.name"),
            @Mapping(source = "application.environment", target = "application.environment"),
            @Mapping(source = "application.host", target = "application.host"),
            @Mapping(source = "application.ip", target = "application.ip"),
            @Mapping(source = "detail.level", target = "level"),
            @Mapping(source = "detail.timestamp", target = "timestamp"),
            @Mapping(source = "detail.content", target = "content"),
            @Mapping(source = "archived", target = "archived"),
            @Mapping(source = "createdBy", target = "createdBy"),
    })
    LogDetailResponseDTO map(Log log);

    List<LogDetailResponseDTO> map(List<Log> logs);

}
