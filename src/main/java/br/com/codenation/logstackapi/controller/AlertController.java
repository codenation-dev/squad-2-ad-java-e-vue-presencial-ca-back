package br.com.codenation.logstackapi.controller;

import br.com.codenation.logstackapi.dto.response.AlertResponseDTO;
import br.com.codenation.logstackapi.dto.response.ErrorResponseDTO;
import br.com.codenation.logstackapi.mappers.AlertMapper;
import br.com.codenation.logstackapi.model.entity.AlertSearch;
import br.com.codenation.logstackapi.service.impl.AlertServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
@Api(tags = {"Alerts"}, description = "Endpoint para gerenciamento dos alertas")
public class AlertController {

    private AlertServiceImpl service;
    private AlertMapper mapper;

    @ApiOperation(
            value = "Recupera todos os alertas cadastrados",
            notes = "Método utilizado para recuperar todos os alertas cadastrados."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = AlertResponseDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorResponseDTO.class)
    })
    @GetMapping(value = "/alerts", produces = MediaType.APPLICATION_JSON_VALUE)
    private Page<AlertResponseDTO> find(@RequestParam(value = "triggerId", required = false) Optional<UUID> logId,
                                        @RequestParam(value = "logId", required = false) Optional<UUID> triggerId,
                                        @RequestParam(value = "page", defaultValue = "0") Integer page,
                                        @RequestParam(value = "size", defaultValue = "20") Integer size) {

        AlertSearch search = AlertSearch.builder()
                .logId(logId.orElse(null))
                .triggerId(triggerId.orElse(null))
                .build();

        return service.find(search, page, size).map(mapper::map);
    }

}