package br.com.codenation.logstackapi.service;

import br.com.codenation.logstackapi.model.entity.Log;

import java.util.List;
import java.util.UUID;

public interface LogService {

    List<Log> findAll();

    Log findById(UUID id);

    List<Log> findByTitle(String title);

    List<Log> findByIp(String ip);

    List<Log> findByLevel(String level);

    List<Log> findByApplicationName(String name);

    List<Log> findByEnvironment(String environment);

    Log unarchive(UUID id);

    Log archive(UUID id);
}
