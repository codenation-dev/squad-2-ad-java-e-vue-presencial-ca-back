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
            @Mapping(source = "server.hostname", target = "server.hostname"),
            @Mapping(source = "server.ip", target = "server.ip"),
            @Mapping(source = "server.application", target = "server.application"),
            @Mapping(source = "server.environment", target = "server.environment"),
            @Mapping(source = "archived", target = "archived"),
            @Mapping(source = "createdDate", target = "createdDate", dateFormat = "yyyy-MM-dd HH:mm")
    })
    LogDTO map(Log log);

    List<LogDTO> map(List<Log> logs);

}
