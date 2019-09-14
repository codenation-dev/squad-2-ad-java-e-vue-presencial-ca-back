package br.com.codenation.logstackapi.controller;

import br.com.codenation.logstackapi.dto.ErrorMessageDTO;
import br.com.codenation.logstackapi.dto.UserCreateDTO;
import br.com.codenation.logstackapi.dto.UserDTO;
import br.com.codenation.logstackapi.mappers.UserMapper;
import br.com.codenation.logstackapi.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Users")
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

private UserServiceImpl service;
    private UserMapper mapper;

    @ApiOperation(
            value = "Recupera todos os usuários cadastrados",
            notes = "Método utilizado para recuperar todos os usuários cadastrados."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorMessageDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorMessageDTO.class)
    })
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    private List<UserDTO> findAll() {
        return mapper.map(service.findAll());
    }

    @ApiOperation(
            value = "Cria um novo usuário",
            notes = "Método utilizado para criar um novo usuário."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuário criado", response = UserDTO.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorMessageDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorMessageDTO.class)
    })
    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    private UserDTO save(@Valid @RequestBody UserCreateDTO dto) {
        return mapper.map(service.save(dto));
    }

}
