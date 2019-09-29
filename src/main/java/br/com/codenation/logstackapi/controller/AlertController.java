package br.com.codenation.logstackapi.controller;

import br.com.codenation.logstackapi.dto.response.AlertResponseDTO;
import br.com.codenation.logstackapi.dto.response.TriggerResponseDTO;
import br.com.codenation.logstackapi.exception.ApiError;
import br.com.codenation.logstackapi.mappers.AlertMapper;
import br.com.codenation.logstackapi.model.entity.User;
import br.com.codenation.logstackapi.service.AlertService;
import br.com.codenation.logstackapi.service.SecurityService;
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
    private List<AlertResponseDTO> findAlerts() {
        User user = securityService.getUserAuthenticated();
        return mapper.map(alertService.findByTriggerCreatedBy(user));
    }

    @ApiOperation(
            value = "Confirmar a visualização de alerta específico.",
            notes = "Método utilizado para confirmar a visualização de um alerta específico."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Alerta visualizado", response = TriggerResponseDTO.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ApiError.class),
            @ApiResponse(code = 404, message = "Alerta não encontrado", response = ApiError.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ApiError.class)
    })
    @PostMapping(value = "/alerts/{id}/view", produces = MediaType.APPLICATION_JSON_VALUE)
    private AlertResponseDTO archive(@PathVariable UUID id) {
        return mapper.map(alertService.visualized(id));
    }


}