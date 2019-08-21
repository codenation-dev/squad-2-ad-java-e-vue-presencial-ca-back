package br.com.codenation.centralerrosapi.controller;

import br.com.codenation.centralerrosapi.controller.exception.StandardError;
import br.com.codenation.centralerrosapi.model.Error;
import br.com.codenation.centralerrosapi.service.ErrorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Api(value = "Errors")
@RequestMapping(value="/errors")
@RestController
public class ErrorController {

    @Autowired
    private ErrorService service;

    @ApiOperation(value = "Return list all errors", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return all errors", response = Error.class, responseContainer = "List")
    })
    @GetMapping
    private List<Error> listAll() {
        return service.listAll();
    }

    @ApiOperation(value = "Return error by ID", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return error by ID", response = Error.class),
            @ApiResponse(code = 404, message = "Error not found", response = StandardError.class)
    })
    @GetMapping("/{id}")
    private Error getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @ApiOperation(value = "Unarchive error by ID", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Unarchive error by ID", response = Error.class),
            @ApiResponse(code = 404, message = "Error not found", response = StandardError.class)
    })
    @PostMapping("/{id}/unarchive")
    private Error unarchive(@PathVariable UUID id) {
        return service.unarchive(id);
    }

}
