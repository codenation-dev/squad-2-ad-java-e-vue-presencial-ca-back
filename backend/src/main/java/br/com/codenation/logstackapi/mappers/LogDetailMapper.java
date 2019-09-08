package br.com.codenation.logstackapi.mappers;

import br.com.codenation.logstackapi.dto.LogDetailDTO;
import br.com.codenation.logstackapi.model.entity.Log;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.io.InvalidObjectException;

@Mapper(componentModel = "spring")
public interface LogDetailMapper {

	LogDetailDTO toDto(Log entity);

	@Mappings({
			@Mapping(source = "detail", target = "detail"),
	})
	Log toEntity(LogDetailDTO dto) throws InvalidObjectException;

}
