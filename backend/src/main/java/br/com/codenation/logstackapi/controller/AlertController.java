package br.com.codenation.logstackapi.controller;

import br.com.codenation.logstackapi.dto.AlertCreateDTO;
import br.com.codenation.logstackapi.dto.ErrorMessageDTO;
import br.com.codenation.logstackapi.model.entity.Alert;
import br.com.codenation.logstackapi.service.AlertService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "Alerts")
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
public class AlertController {

    private AlertService service;

    @ApiOperation(
            value = "Cria um novo alerta",
            notes = "Método utilizado para criar um novo."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Alerta criado", response = Alert.class),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorMessageDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorMessageDTO.class)
    })
    @PostMapping(value = "/alerts", produces = MediaType.APPLICATION_JSON_VALUE)
    private Alert save(@Valid @RequestBody AlertCreateDTO dto) {
        return service.save(dto);
    }

}
