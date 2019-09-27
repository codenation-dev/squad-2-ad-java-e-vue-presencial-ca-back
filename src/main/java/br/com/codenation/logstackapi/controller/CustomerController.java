package br.com.codenation.logstackapi.controller;

import br.com.codenation.logstackapi.dto.response.CustomerResponseDTO;
import br.com.codenation.logstackapi.dto.response.ErrorResponseDTO;
import br.com.codenation.logstackapi.mappers.CustomerMapper;
import br.com.codenation.logstackapi.model.entity.Customer;
import br.com.codenation.logstackapi.model.entity.User;
import br.com.codenation.logstackapi.service.impl.CustomerServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
@Api(tags = {"Customers"}, description = "Endpoint para gerenciamento dos clientes")
public class CustomerController {

    private CustomerServiceImpl service;
    private CustomerMapper mapper;

    @ApiOperation(
            value = "Recupera dados do cliente autenticado",
            notes = "Método utilizado para recuperar dados do cliente autenticado."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Customer.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorResponseDTO.class)
    })
    @GetMapping(value = "/customers/self", produces = MediaType.APPLICATION_JSON_VALUE)
    private CustomerResponseDTO self() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = service.findByUser(user).get();
        return mapper.map(customer);
    }

}
