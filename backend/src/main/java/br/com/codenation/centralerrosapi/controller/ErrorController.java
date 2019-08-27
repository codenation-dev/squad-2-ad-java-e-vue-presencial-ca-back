package br.com.codenation.centralerrosapi.controller;

import br.com.codenation.centralerrosapi.controller.exception.StandardError;
import br.com.codenation.centralerrosapi.model.Error;
import br.com.codenation.centralerrosapi.service.ErrorService;
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
@Api(value = "Errors")
@RequestMapping(value="/api/v1")
public class ErrorController {

    @Autowired
    private ErrorService service;

    @ApiOperation(
            value = "Recupera todos os erros cadastrados",
            notes = "Método utilizado para recuperar todos os erros cadastrados."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Page.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = StandardError.class),
            @ApiResponse(code = 500, message = "Erro na api", response = StandardError.class)
    })
    @GetMapping(value = "/errors", produces = MediaType.APPLICATION_JSON_VALUE)
    private Page<Error> listAll(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                @RequestParam(value = "per_page", required = false, defaultValue = "10") Integer per_page,
                                @RequestParam(value = "sort", required = false, defaultValue = "id") String sort) {
        return service.listAll(page, per_page, sort);
    }

    @ApiOperation(
            value = "Recupera um erro específico.",
            notes = "Método utilizado para recuperar um erro específico."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Error.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = StandardError.class),
            @ApiResponse(code = 404, message = "Erro não encontrado", response = StandardError.class),
            @ApiResponse(code = 500, message = "Erro na api", response = StandardError.class)
    })
    @GetMapping(value = "/errors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private Error getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @ApiOperation(
            value = "Arquiva um erro específico.",
            notes = "Método utilizado para arquivar um erro específico."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Erro arquivado", response = Error.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = StandardError.class),
            @ApiResponse(code = 404, message = "Erro não encontrado", response = StandardError.class),
            @ApiResponse(code = 500, message = "Erro na api", response = StandardError.class)
    })
    @PostMapping(value = "/errors/{id}/archive", produces = MediaType.APPLICATION_JSON_VALUE)
    private Error archive(@PathVariable UUID id){
        return service.archive(id);
    }

    @ApiOperation(
            value = "Desarquiva um erro específico.",
            notes = "Método utilizado para desarquivar um erro específico."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Erro desarquivado", response = Error.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = StandardError.class),
            @ApiResponse(code = 404, message = "Erro não encontrado", response = StandardError.class),
            @ApiResponse(code = 500, message = "Erro na api", response = StandardError.class)
    })
    @PostMapping(value = "/errors/{id}/unarchive", produces = MediaType.APPLICATION_JSON_VALUE)
    private Error unarchive(@PathVariable UUID id) {
        return service.unarchive(id);
    }

}
