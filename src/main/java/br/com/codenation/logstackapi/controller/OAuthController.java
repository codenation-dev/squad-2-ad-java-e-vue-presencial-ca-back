package br.com.codenation.logstackapi.controller;

import br.com.codenation.logstackapi.dto.request.UserRequestDTO;
import br.com.codenation.logstackapi.dto.response.ErrorResponseDTO;
import br.com.codenation.logstackapi.dto.response.UserResponseDTO;
import br.com.codenation.logstackapi.mappers.UserMapper;
import br.com.codenation.logstackapi.model.entity.User;
import br.com.codenation.logstackapi.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping
@Api(tags = {"Authentication"}, description = "Endpoint para gerenciamento dos usuários")
public class OAuthController {

    private UserServiceImpl service;
    private UserMapper mapper;

    @ApiOperation(
            value = "Cria um novo usuário",
            notes = "Método utilizado para criar um novo usuário."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuário criado", response = UserResponseDTO.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorResponseDTO.class)
    })
    @PostMapping(value = "/oauth/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    private UserResponseDTO save(@Valid @RequestBody UserRequestDTO dto) {
        return mapper.map(service.save(dto));
    }

    @ApiOperation(
            value = "Recupera dados do usuário autenticado",
            notes = "Método utilizado para recuperar dados do usuário autenticado."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserResponseDTO.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorResponseDTO.class)
    })
    @GetMapping(value = "/oauth/self", produces = MediaType.APPLICATION_JSON_VALUE)
    private UserResponseDTO self() {
        User userAuth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return mapper.map(service.findByEmail(userAuth.getEmail()).get());
    }

}
