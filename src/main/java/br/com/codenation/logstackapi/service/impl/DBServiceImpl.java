package br.com.codenation.logstackapi.service.impl;

import br.com.codenation.logstackapi.model.entity.*;
import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import br.com.codenation.logstackapi.model.enums.LogLevel;
import br.com.codenation.logstackapi.repository.LogRepository;
import br.com.codenation.logstackapi.repository.TriggerRepository;
import br.com.codenation.logstackapi.repository.UserRepository;
import br.com.codenation.logstackapi.service.DBService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DBServiceImpl implements DBService {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityServiceImpl securityService;

    @Autowired
    private TriggerRepository triggerRepository;

    public void instantiateTestDatabase() throws ParseException {
        log.info("Started database environment dev");
        User user = User.builder().email("admin@admin.com").fullName("Administrador").password("admin").build();
        user = userRepository.save(user);
        log.info("User created: " + user);
    }

    public void instantiateDevDatabase() throws ParseException {

        log.info("Started database environment dev");

        User user = User.builder().email("admin@admin.com").fullName("Administrador").password("admin").build();
        userRepository.save(user);

        securityService.autoLogin(user.getEmail(), user.getPassword());

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
