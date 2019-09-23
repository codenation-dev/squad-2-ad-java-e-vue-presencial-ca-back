package br.com.codenation.logstackapi.service;

import br.com.codenation.logstackapi.dto.request.LogRequestDTO;
import br.com.codenation.logstackapi.dto.request.LogSearchDTO;
import br.com.codenation.logstackapi.model.entity.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface LogService {

    List<Log> findByCheckAlertNotVerified(Integer size);

    Page<Log> find(LogSearchDTO search, Integer page, Integer size, Sort sort);

    Log findById(UUID id);

    Log save(LogRequestDTO dto);

    Log unarchive(UUID id);

    Log archive(UUID id);

    Log checkAlert(UUID id, Boolean checkAlert);

}
