package br.com.codenation.centralerrosapi.controllers;

import br.com.codenation.centralerrosapi.models.Error;
import br.com.codenation.centralerrosapi.services.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value="/errors")
public class ErrorController {

    @Autowired
    private ErrorService service;

    @GetMapping
    private List<Error> listAll() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    private Error getById(@PathVariable UUID id) {
        return service.findById(id);
    }
}
