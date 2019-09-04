package br.com.codenation.centralerrosapi.controller;

import br.com.codenation.centralerrosapi.controller.exception.ErrorMessage;
import br.com.codenation.centralerrosapi.dto.LogDTO;
import br.com.codenation.centralerrosapi.dto.LogDetailDTO;
import br.com.codenation.centralerrosapi.mappers.LogDetailMapper;
import br.com.codenation.centralerrosapi.mappers.LogMapper;
import br.com.codenation.centralerrosapi.model.Log;
import br.com.codenation.centralerrosapi.service.LogService;
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

    private LogService service;
    private LogMapper mapper;
    private LogDetailMapper mapperDetail;

    @ApiOperation(
            value = "Recupera todos os logs cadastrados",
            notes = "Método utilizado para recuperar todos os logs cadastrados."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = LogDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorMessage.class)
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
            @ApiResponse(code = 200, message = "OK", response = Log.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorMessage.class),
            @ApiResponse(code = 404, message = "Log não encontrado", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorMessage.class)
    })
    @GetMapping(value = "/logs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private LogDetailDTO getById(@PathVariable UUID id) {
        return mapperDetail.toDto(service.findById(id));
    }

    @ApiOperation(
            value = "Arquiva um log específico.",
            notes = "Método utilizado para arquivar um log específico."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Log arquivado", response = Log.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorMessage.class),
            @ApiResponse(code = 404, message = "Log não encontrado", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorMessage.class)
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
            @ApiResponse(code = 200, message = "Log desarquivado", response = Log.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorMessage.class),
            @ApiResponse(code = 404, message = "Log não encontrado", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorMessage.class)
    })
    @DeleteMapping(value = "/logs/{id}/unarchive", produces = MediaType.APPLICATION_JSON_VALUE)
    private LogDTO unarchive(@PathVariable UUID id) {
        return mapper.map(service.unarchive(id));
    }

}
