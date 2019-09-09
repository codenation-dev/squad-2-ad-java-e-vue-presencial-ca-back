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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @ApiOperation(
            value = "Recupera todos os alertas cadastrados",
            notes = "Método utilizado para recuperar todos os alertas cadastrados."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Alert.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Requisição mal formatada", response = ErrorMessageDTO.class),
            @ApiResponse(code = 500, message = "Erro na api", response = ErrorMessageDTO.class)
    })
    @GetMapping(value = "/alerts", produces = MediaType.APPLICATION_JSON_VALUE)
    private List<Alert> findAll() {
        return service.findAll();
    }

}
