package br.com.codenation.centralerrosapi.controller;

import br.com.codenation.centralerrosapi.controller.exception.ErrorMessage;
import br.com.codenation.centralerrosapi.model.Log;
import br.com.codenation.centralerrosapi.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Api(value = "Logs")
@RequestMapping(value="/api/v1")
public class LogController {

    @Autowired
    private LogService service;

    @ApiOperation(
            value = "Recupera todos os logs cadastrados",
            notes = "Método utilizado para recuperar todos os logs cadastrados."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Page.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorMessage.class)
    })
    @GetMapping(value = "/logs", produces = MediaType.APPLICATION_JSON_VALUE)
    private Page<Log> listAll(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                              @RequestParam(value = "per_page", required = false, defaultValue = "10") Integer per_page,
                              @RequestParam(value = "sort", required = false, defaultValue = "id") String sort) {
        return service.listAll(page, per_page, sort);
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
    private Log getById(@PathVariable UUID id) {
        return service.findById(id);
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
    @PostMapping(value = "/logs/{id}/archive", produces = MediaType.APPLICATION_JSON_VALUE)
    private Log archive(@PathVariable UUID id){
        return service.archive(id);
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
    @PostMapping(value = "/logs/{id}/unarchive", produces = MediaType.APPLICATION_JSON_VALUE)
    private Log unarchive(@PathVariable UUID id) {
        return service.unarchive(id);
    }

}
