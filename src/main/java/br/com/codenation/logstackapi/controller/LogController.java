package br.com.codenation.logstackapi.controller;

import br.com.codenation.logstackapi.dto.ErrorMessageDTO;
import br.com.codenation.logstackapi.dto.LogSearchDTO;
import br.com.codenation.logstackapi.dto.request.LogRequestDTO;
import br.com.codenation.logstackapi.dto.response.LogDetailResponseDTO;
import br.com.codenation.logstackapi.dto.response.LogResponseDTO;
import br.com.codenation.logstackapi.mappers.LogDetailMapper;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

@Api(value = "Logs")
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
public class LogController {

    private LogServiceImpl service;
    private LogMapper mapper;
    private LogDetailMapper mapperDetail;

    @ApiOperation(
            value = "Recupera todos os logs cadastrados",
            notes = "Método utilizado para recuperar todos os logs cadastrados."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = LogResponseDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorMessageDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorMessageDTO.class)
    })
    @GetMapping(value = "/logs", produces = MediaType.APPLICATION_JSON_VALUE)
    private Page<LogResponseDTO> find(
            @RequestParam(value = "title", required = false) Optional<String> title,
            @RequestParam(value = "app_name", required = false) Optional<String> appName,
            @RequestParam(value = "host", required = false) Optional<String> host,
            @RequestParam(value = "ip", required = false) Optional<String> ip,
            @RequestParam(value = "environment", required = false) Optional<LogEnvironment> environment,
            @RequestParam(value = "level", required = false) Optional<LogLevel> level,
            @RequestParam(value = "startTimestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startTimestamp,
            @RequestParam(value = "endTimestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endTimestamp,
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
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorMessageDTO.class),
            @ApiResponse(code = 404, message = "Log não encontrado", response = ErrorMessageDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorMessageDTO.class)
    })
    @GetMapping(value = "/logs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private LogDetailResponseDTO getById(@PathVariable UUID id) {
        return mapperDetail.map(service.findById(id));
    }

    @ApiOperation(
            value = "Arquiva um log específico.",
            notes = "Método utilizado para arquivar um log específico."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Log arquivado", response = LogResponseDTO.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorMessageDTO.class),
            @ApiResponse(code = 404, message = "Log não encontrado", response = ErrorMessageDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorMessageDTO.class)
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
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorMessageDTO.class),
            @ApiResponse(code = 404, message = "Log não encontrado", response = ErrorMessageDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorMessageDTO.class)
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
            @ApiResponse(code = 400, message = "Requisão mal formatada", response = ErrorMessageDTO.class),
            @ApiResponse(code = 500, message = "Erro na apo", response = ErrorMessageDTO.class)
    })
    @PostMapping(value = "/logs", produces = MediaType.APPLICATION_JSON_VALUE)
    private LogResponseDTO save(LogRequestDTO dto) {
        return mapper.map(service.save(dto));
    }
}
