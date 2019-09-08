package br.com.codenation.logstackapi.controller;

import br.com.codenation.logstackapi.dto.ErrorMessageDTO;
import br.com.codenation.logstackapi.model.User;
import br.com.codenation.logstackapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Users")
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

    private UserService service;

    @ApiOperation(
            value = "Recupera todos os usuários cadastrados",
            notes = "Método utilizado para recuperar todos os usuários cadastrados."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorMessageDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorMessageDTO.class)
    })
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    private List<User> findAll() {
        return service.findAll();
    }

}
