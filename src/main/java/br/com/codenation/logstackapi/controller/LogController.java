package br.com.codenation.logstackapi.controller;

import br.com.codenation.logstackapi.dto.request.LogRequestDTO;
import br.com.codenation.logstackapi.dto.request.LogSearchDTO;
import br.com.codenation.logstackapi.dto.response.ErrorResponseDTO;
import br.com.codenation.logstackapi.dto.response.LogDetailResponseDTO;
import br.com.codenation.logstackapi.dto.response.LogResponseDTO;
import br.com.codenation.logstackapi.mappers.LogMapper;
import br.com.codenation.logstackapi.model.entity.Log;
import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import br.com.codenation.logstackapi.model.enums.LogLevel;
import br.com.codenation.logstackapi.service.impl.LogServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
@Api(tags = {"Logs"}, description = "Endpoint para gerenciamento dos logs")
public class LogController {

    private LogServiceImpl service;
    private LogMapper mapper;

    @ApiOperation(
            value = "Recupera todos os logs cadastrados",
            notes = "Método utilizado para recuperar todos os logs cadastrados."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = LogResponseDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorResponseDTO.class)
    })
    @GetMapping(value = "/logs", produces = MediaType.APPLICATION_JSON_VALUE)
    private Page<LogResponseDTO> find(
            @RequestParam(value = "title", required = false) Optional<String> title,
            @RequestParam(value = "app_name", required = false) Optional<String> appName,
            @RequestParam(value = "host", required = false) Optional<String> host,
            @RequestParam(value = "ip", required = false) Optional<String> ip,
            @RequestParam(value = "environment", required = false) Optional<LogEnvironment> environment,
            @RequestParam(value = "level", required = false) Optional<LogLevel> level,
            @RequestParam(value = "startTimestamp", required = false, defaultValue = "2019-09-01")
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startTimestamp,
            @RequestParam(value = "endTimestamp", required = false, defaultValue = "2019-09-30")
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endTimestamp,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size) {

        Sort sort = Sort.by(Sort.Direction.DESC, "detail.timestamp");

        LogSearchDTO search = LogSearchDTO.builder()
                .title(title.map(String::toLowerCase).orElse(null))
                .appName(appName.map(String::toLowerCase).orElse(null))
                .host(host.map(String::toLowerCase).orElse(null))
                .ip(ip.map(String::toLowerCase).orElse(null))
                .environment(environment.orElse(null))
                .level(level.orElse(null))
                .startTimestamp(LocalDateTime.of(startTimestamp, LocalTime.of(0, 0, 0)))
                .endTimestamp(LocalDateTime.of(endTimestamp, LocalTime.of(23, 59, 59)))
                .build();

        Page<Log> logs = service.find(search, page, size, sort);
        return logs.map(p -> mapper.map(p));
    }

    @ApiOperation(
            value = "Recupera um log específico.",
            notes = "Método utilizado para recuperar um log específico."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = LogDetailResponseDTO.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorResponseDTO.class),
            @ApiResponse(code = 404, message = "Log não encontrado", response = ErrorResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorResponseDTO.class)
    })
    @GetMapping(value = "/logs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private LogResponseDTO getById(@PathVariable UUID id) {
        return mapper.map(service.findById(id));
    }

    @ApiOperation(
            value = "Arquiva um log específico.",
            notes = "Método utilizado para arquivar um log específico."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Log arquivado", response = LogResponseDTO.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorResponseDTO.class),
            @ApiResponse(code = 404, message = "Log não encontrado", response = ErrorResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorResponseDTO.class)
    })
    @PutMapping(value = "/logs/{id}/archive", produces = MediaType.APPLICATION_JSON_VALUE)
    private LogResponseDTO archive(@PathVariable UUID id) {
        return mapper.map(service.archive(id));
    }

    @ApiOperation(
            value = "Desarquiva um log específico.",
            notes = "Método utilizado para desarquivar um log específico."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Log desarquivado", response = LogResponseDTO.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorResponseDTO.class),
            @ApiResponse(code = 404, message = "Log não encontrado", response = ErrorResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorResponseDTO.class)
    })
    @DeleteMapping(value = "/logs/{id}/archive", produces = MediaType.APPLICATION_JSON_VALUE)
    private LogResponseDTO unarchive(@PathVariable UUID id) {
        return mapper.map(service.unarchive(id));
    }

    @ApiOperation(
            value = "Cria um log",
            notes = "Método utilizado para criar um log"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Log criado", response = LogResponseDTO.class),
            @ApiResponse(code = 400, message = "Requisão mal formatada", response = ErrorResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro na apo", response = ErrorResponseDTO.class)
    })
    @PostMapping(value = "/logs", produces = MediaType.APPLICATION_JSON_VALUE)
    private LogResponseDTO save(@Valid @RequestBody LogRequestDTO dto) {
        return mapper.map(service.save(dto));
    }
}
