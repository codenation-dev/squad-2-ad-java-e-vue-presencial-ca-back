package br.com.codenation.logstackapi.controller;

import br.com.codenation.logstackapi.dto.response.AlertResponseDTO;
import br.com.codenation.logstackapi.exception.ApiError;
import br.com.codenation.logstackapi.mappers.AlertMapper;
import br.com.codenation.logstackapi.model.entity.User;
import br.com.codenation.logstackapi.service.impl.AlertService;
import br.com.codenation.logstackapi.service.impl.SecurityService;
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

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
@Api(tags = {"Alerts"}, description = "Endpoint para gerenciamento dos alertas")
public class AlertController {

    private SecurityService securityService;
    private AlertService alertService;
    private AlertMapper mapper;

    @ApiOperation(
            value = "Recupera todos os alertas cadastrados",
            notes = "Método utilizado para recuperar todos os alertas cadastrados."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = AlertResponseDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ApiError.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ApiError.class)
    })
    @GetMapping(value = "/alerts", produces = MediaType.APPLICATION_JSON_VALUE)
    private List<AlertResponseDTO> findAll() {
        User user = securityService.getUserAuthenticated();
        return mapper.map(alertService.findByTriggerCreatedBy(user));
    }

}