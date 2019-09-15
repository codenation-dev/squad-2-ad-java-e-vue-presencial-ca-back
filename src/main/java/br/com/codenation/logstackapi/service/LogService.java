package br.com.codenation.logstackapi.service;

import br.com.codenation.logstackapi.dto.request.LogSearchDTO;
import br.com.codenation.logstackapi.model.entity.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.UUID;

public interface LogService {

    Page<Log> find(LogSearchDTO search, Integer page, Integer size, Sort sort);

    Log findById(UUID id);

    Log unarchive(UUID id);

    Log archive(UUID id);
}
