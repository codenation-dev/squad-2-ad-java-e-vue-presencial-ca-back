package br.com.codenation.logstackapi.controller;

import br.com.codenation.logstackapi.dto.request.UserRequestDTO;
import br.com.codenation.logstackapi.dto.response.UserResponseDTO;
import br.com.codenation.logstackapi.exception.ApiError;
import br.com.codenation.logstackapi.mappers.UserMapper;
import br.com.codenation.logstackapi.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
@Api(tags = {"Users"}, description = "Endpoint para gerenciamento dos usuários")
public class UserController {

    private UserServiceImpl service;
    private UserMapper mapper;

    @ApiOperation(
            value = "Recupera todos os usuários cadastrados",
            notes = "Método utilizado para recuperar todos os usuários cadastrados."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserResponseDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ApiError.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ApiError.class)
    })
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    private List<UserResponseDTO> findAll() {
        return mapper.map(service.findAll());
    }

    @ApiOperation(
            value = "Recupera dados do usuário por ID",
            notes = "Método utilizado para recuperar dados do usuário por ID."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserResponseDTO.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ApiError.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ApiError.class)
    })
    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private UserResponseDTO findById(@PathVariable UUID id) {
        return mapper.map(service.findById(id));
    }

    @ApiOperation(
            value = "Altera dados do usuário autenticado",
            notes = "Método utilizado para alterar dados do usuário autenticado."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserResponseDTO.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorResponseDTO.class)
    })
    @PutMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private UserResponseDTO update(@PathVariable UUID id, @RequestBody UserRequestDTO dto) {
        return mapper.map(service.update(id, dto));
    }

}
