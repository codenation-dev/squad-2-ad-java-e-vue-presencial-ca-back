package br.com.codenation.logstackapi.controller;

import br.com.codenation.logstackapi.dto.request.TriggerRequestDTO;
import br.com.codenation.logstackapi.dto.response.ErrorResponseDTO;
import br.com.codenation.logstackapi.dto.response.TriggerResponseDTO;
import br.com.codenation.logstackapi.mappers.TriggerMapper;
import br.com.codenation.logstackapi.service.impl.TriggerServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Api(value = "Triggers")
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
public class TriggerController {

    private TriggerServiceImpl service;
    private TriggerMapper mapper;

    @ApiOperation(
            value = "Cria um novo gatilho",
            notes = "Método utilizado para criar um novo."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Gatilho criado", response = TriggerResponseDTO.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorResponseDTO.class)
    })
    @PostMapping(value = "/triggers", produces = MediaType.APPLICATION_JSON_VALUE)
    private TriggerResponseDTO save(@Valid @RequestBody TriggerRequestDTO dto) {
        return mapper.map(service.save(dto));
    }

    @ApiOperation(
            value = "Recupera todos os gatilhos cadastrados",
            notes = "Método utilizado para recuperar todos os Triggeras cadastrados."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TriggerResponseDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorResponseDTO.class)
    })
    @GetMapping(value = "/triggers", produces = MediaType.APPLICATION_JSON_VALUE)
    private List<TriggerResponseDTO> findAll() {
        return mapper.map(service.findAll());
    }

    @ApiOperation(
            value = "Recupera um gatilho específico.",
            notes = "Método utilizado para recuperar um gatilho específico."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TriggerResponseDTO.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorResponseDTO.class)
    })
    @GetMapping(value = "/triggers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private TriggerResponseDTO findById(@PathVariable UUID id) {
        return mapper.map(service.findById(id));
    }

    @ApiOperation(
            value = "Ativar um gatilho específico.",
            notes = "Método utilizado para ativar um gatilho específico."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Gatilho ativo", response = TriggerResponseDTO.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorResponseDTO.class),
            @ApiResponse(code = 404, message = "Gatilho não encontrado", response = ErrorResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorResponseDTO.class)
    })  
    @PutMapping(value = "/triggers/{id}/active", produces = MediaType.APPLICATION_JSON_VALUE)
    private TriggerResponseDTO active(@PathVariable UUID id){
        return mapper.map(service.active(id));
    }

    @ApiOperation(
            value = "Desativar um gatilho específico.",
            notes = "Método utilizado para desativar um gatilho específico."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Gatilho desativado", response = TriggerResponseDTO.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorResponseDTO.class),
            @ApiResponse(code = 404, message = "Gatilho não encontrado", response = ErrorResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorResponseDTO.class)
    })
    @DeleteMapping(value = "/triggers/{id}/active", produces = MediaType.APPLICATION_JSON_VALUE)
    private TriggerResponseDTO inactive(@PathVariable UUID id) {
        return mapper.map(service.inactive(id));
    }              

    @ApiOperation(
            value = "Arquivar um gatilho específico.",
            notes = "Método utilizado para arquivar um gatilho específico."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Gatilho arquivado", response = TriggerResponseDTO.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorResponseDTO.class),
            @ApiResponse(code = 404, message = "Gatilho não encontrado", response = ErrorResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorResponseDTO.class)
    })
    @PutMapping(value = "/triggers/{id}/archive", produces = MediaType.APPLICATION_JSON_VALUE)
    private TriggerResponseDTO archive(@PathVariable UUID id) {
        return mapper.map(service.archive(id));
    }
  
    @ApiOperation(
            value = "Desarquivar um gatilho específico.",
            notes = "Método utilizado para desarquivar um gatilho específico."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Gatilho desarquivado", response = TriggerResponseDTO.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorResponseDTO.class),
            @ApiResponse(code = 404, message = "Gatilho não encontrado", response = ErrorResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorResponseDTO.class)
    })
    @DeleteMapping(value = "/triggers/{id}/archive", produces = MediaType.APPLICATION_JSON_VALUE)
    private TriggerResponseDTO unarchive(@PathVariable UUID id) {
        return mapper.map(service.unarchive(id));
    }  

}