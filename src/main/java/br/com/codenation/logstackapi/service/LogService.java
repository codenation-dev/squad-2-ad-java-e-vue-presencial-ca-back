package br.com.codenation.logstackapi.service;

import br.com.codenation.logstackapi.model.entity.Log;
import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import br.com.codenation.logstackapi.model.enums.LogLevel;

import java.util.List;
import java.util.UUID;

public interface LogService {

    List<Log> findAll();

    Log findById(UUID id);

    List<Log> findByTitle(String title);

    List<Log> findByIp(String ip);

    List<Log> findByLevel(LogLevel level);

    List<Log> findByApplicationName(String name);

    List<Log> findByEnvironment(LogEnvironment environment);

    Log unarchive(UUID id);

    Log archive(UUID id);
}
