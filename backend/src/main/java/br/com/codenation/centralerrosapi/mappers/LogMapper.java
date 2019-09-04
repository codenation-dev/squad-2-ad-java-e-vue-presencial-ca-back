package br.com.codenation.centralerrosapi.mappers;

import br.com.codenation.centralerrosapi.dto.LogDTO;
import br.com.codenation.centralerrosapi.model.Log;
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
            @Mapping(source = "application.hostname", target = "application.hostname"),
            @Mapping(source = "application.ip", target = "application.ip"),
            @Mapping(source = "archived", target = "archived"),
            @Mapping(source = "createdDate", target = "createdDate", dateFormat = "yyyy-MM-dd HH:mm")
    })
    LogDTO map(Log log);

    List<LogDTO> map(List<Log> logs);

}
