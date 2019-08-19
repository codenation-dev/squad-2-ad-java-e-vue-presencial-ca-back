package br.com.codenation.centralerrosapi.controller;

import br.com.codenation.centralerrosapi.model.Error;
import br.com.codenation.centralerrosapi.service.ErrorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Api("Errors")
@RequestMapping(value="/errors")
@RestController
@AllArgsConstructor
public class ErrorController {

    private ErrorService service;

    @ApiOperation(value = "Retorna uma lista de erros")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private List<Error> listAll() {
        return service.listAll();
    }

    @ApiOperation(value = "Retorna o erro por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Error not found")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private Error getById(@PathVariable UUID id) {
        return service.findById(id);
    }

}
