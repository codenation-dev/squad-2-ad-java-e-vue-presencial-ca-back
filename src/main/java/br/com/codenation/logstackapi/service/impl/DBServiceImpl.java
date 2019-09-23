package br.com.codenation.logstackapi.service.impl;

import br.com.codenation.logstackapi.dto.request.UserRequestDTO;
import br.com.codenation.logstackapi.model.entity.*;
import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import br.com.codenation.logstackapi.model.enums.LogLevel;
import br.com.codenation.logstackapi.repository.LogRepository;
import br.com.codenation.logstackapi.repository.TriggerRepository;
import br.com.codenation.logstackapi.service.DBService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DBServiceImpl implements DBService {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private SecurityServiceImpl securityService;

    @Autowired
    private TriggerRepository triggerRepository;

    public void instantiateTestDatabase() {
        log.info("Started database environment dev");

        String fullName = "Administrador";
        String username = "admin@admin.com";
        String password = "admin";

        UserRequestDTO dto = UserRequestDTO.builder().email(username).fullName(fullName).password(password).build();
        User user = userService.save(dto);
        log.info("User created: " + user);
        log.info("Finished database");

    }

    public void instantiateDevDatabase() {

        log.info("Started database environment dev");

        String fullName = "Administrador";
        String username = "admin@admin.com";
        String password = "admin";

        UserRequestDTO dto = UserRequestDTO.builder().email(username).fullName(fullName).password(password).build();
        User user = userService.save(dto);
        log.info("User created: " + user);

        securityService.autoLogin(username, password);

        TriggerFilter filter = TriggerFilter.builder()
                .appName("logstack-api")
                .environment(LogEnvironment.PRODUCTION)
                .level(LogLevel.ERROR)
                .build();

        Trigger trigger = Trigger.builder()
                .name("Level Error em Produção na Aplicação LOGSTACK-API")
                .email("luannn@gmail.com")
                .message("Verificar o erro em produção na API LOGSTACK")
                .active(true)
                .archived(false)
                .filters(filter)
                .alerts(new ArrayList<>())
                .build();

        triggerRepository.save(trigger);

        LocalDateTime timestamp = LocalDateTime.now();

        LogApplication application = LogApplication.builder()
                .name("logstack-api").ip("127.0.0.1").host("java-client-test").environment(LogEnvironment.PRODUCTION).build();

        List<Log> logs = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            LogDetail detail = LogDetail.builder()
                    .timestamp(timestamp).level(LogLevel.ERROR).content("Detalhe do log " + i).build();
            Log log = Log.builder()
                    .title("Título do log " + i).application(application).detail(detail).archived(false).checkAlert(false).build();
            logs.add(log);
        }

        logRepository.saveAll(logs);

        log.info("Finished database");

    }

}
