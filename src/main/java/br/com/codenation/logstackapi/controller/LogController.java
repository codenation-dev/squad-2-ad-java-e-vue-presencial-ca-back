package br.com.codenation.logstackapi.controller;

import br.com.codenation.logstackapi.dto.ErrorMessageDTO;
import br.com.codenation.logstackapi.dto.LogDTO;
import br.com.codenation.logstackapi.dto.LogDetailDTO;
import br.com.codenation.logstackapi.mappers.LogDetailMapper;
import br.com.codenation.logstackapi.mappers.LogMapper;
import br.com.codenation.logstackapi.service.impl.LogServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Api(value = "Logs")
@AllArgsConstructor
@RestController
@RequestMapping(value="/api/v1")
public class LogController {

    private LogServiceImpl service;
    private LogMapper mapper;
    private LogDetailMapper mapperDetail;

    @ApiOperation(
            value = "Recupera todos os logs cadastrados",
            notes = "Método utilizado para recuperar todos os logs cadastrados."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = LogDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorMessageDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorMessageDTO.class)
    })
    @GetMapping(value = "/logs", produces = MediaType.APPLICATION_JSON_VALUE)
    private List<LogDTO> find(@RequestParam Optional<String> title,
                              @RequestParam Optional<String> ip,
                              @RequestParam Optional<String> appName,
                              @RequestParam Optional<String> environment,
                              @RequestParam Optional<String> level) {

        if (title.isPresent()) return mapper.map(service.findByTitle(title.get()));
        if (ip.isPresent()) return mapper.map(service.findByIp(ip.get()));
        if (appName.isPresent()) return mapper.map(service.findByApplicationName(appName.get()));
        if (environment.isPresent()) return mapper.map(service.findByEnvironment(environment.get()));
        if (level.isPresent()) return mapper.map(service.findByLevel(level.get()));

        return mapper.map(service.findAll());
    }

    @ApiOperation(
            value = "Recupera um log específico.",
            notes = "Método utilizado para recuperar um log específico."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = LogDetailDTO.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorMessageDTO.class),
            @ApiResponse(code = 404, message = "Log não encontrado", response = ErrorMessageDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorMessageDTO.class)
    })
    @GetMapping(value = "/logs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private LogDetailDTO getById(@PathVariable UUID id) {
        return mapperDetail.map(service.findById(id));
    }

    @ApiOperation(
            value = "Arquiva um log específico.",
            notes = "Método utilizado para arquivar um log específico."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Log arquivado", response = LogDTO.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorMessageDTO.class),
            @ApiResponse(code = 404, message = "Log não encontrado", response = ErrorMessageDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorMessageDTO.class)
    })
    @PutMapping(value = "/logs/{id}/archive", produces = MediaType.APPLICATION_JSON_VALUE)
    private LogDTO archive(@PathVariable UUID id){
        return mapper.map(service.archive(id));
    }

    @ApiOperation(
            value = "Desarquiva um log específico.",
            notes = "Método utilizado para desarquivar um log específico."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Log desarquivado", response = LogDTO.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorMessageDTO.class),
            @ApiResponse(code = 404, message = "Log não encontrado", response = ErrorMessageDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorMessageDTO.class)
    })
    @DeleteMapping(value = "/logs/{id}/archive", produces = MediaType.APPLICATION_JSON_VALUE)
    private LogDTO unarchive(@PathVariable UUID id) {
        return mapper.map(service.unarchive(id));
    }

}
