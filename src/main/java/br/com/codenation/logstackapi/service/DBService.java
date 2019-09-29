package br.com.codenation.logstackapi.service;

import br.com.codenation.logstackapi.dto.request.*;
import br.com.codenation.logstackapi.model.entity.Customer;
import br.com.codenation.logstackapi.model.entity.Log;
import br.com.codenation.logstackapi.model.entity.Trigger;
import br.com.codenation.logstackapi.model.entity.User;
import br.com.codenation.logstackapi.model.enums.LogEnvironment;
import br.com.codenation.logstackapi.model.enums.LogLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class DBService {

    @Autowired
    private LogService logService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TriggerService triggerService;

    @Autowired
    private SecurityService securityService;

    public void instantiateTestDatabase() {
        log.info("Started database environment dev");

        String fullName = "Administrador";
        String username = "admin@admin.com";
        String password = "admin";

        UserRequestDTO userDTO = UserRequestDTO.builder().email(username).fullName(fullName).password(password).build();
        User user = userService.save(userDTO);
        log.info("User created: " + user);

        Optional<Customer> customer = customerService.findByUser(user);
        log.info("Customer created: " + customer);

        log.info("Finished database");

    }

    public void instantiateDevDatabase() {

        log.info("Started database environment dev");

        String fullName = "Administrador";
        String username = "admin@admin.com";
        String password = "admin";

        UserRequestDTO userDTO = UserRequestDTO.builder().email(username).fullName(fullName).password(password).build();
        User user = userService.save(userDTO);
        log.info("User created: " + user);

        Optional<Customer> customer = customerService.findByUser(user);
        log.info("Customer created: " + customer);

        securityService.autoLogin(username, password);

        TriggerFilterRequestDTO filter = TriggerFilterRequestDTO.builder()
                .appName("logstack-api")
                .environment(LogEnvironment.PRODUCTION)
                .level(LogLevel.ERROR)
                .build();

        TriggerRequestDTO filterDTO = TriggerRequestDTO.builder()
                .name("Level Error em Produção na Aplicação LOGSTACK-API")
                .email("luannn@gmail.com")
                .message("Verificar o erro em produção na API LOGSTACK")
                .isActive(true)
                .filters(filter)
                .build();

        Trigger trigger = triggerService.save(filterDTO);
        log.info("Trigger created: " + trigger);

        UUID apiKey = customer.get().getApiKey();

        LogApplicationRequestDTO applicationDTO = LogApplicationRequestDTO.builder()
                .name("logstack-api")
                .ip("127.0.0.1")
                .host("java-client-test")
                .environment(LogEnvironment.PRODUCTION)
                .build();

        List<Log> logs = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            LocalDateTime timestamp = LocalDateTime.now();
            LogRequestDTO dto = LogRequestDTO.builder()
                    .title("Título do log " + i)
                    .timestamp(timestamp).level(LogLevel.ERROR).content("Detalhe do log " + i)
                    .application(applicationDTO)
                    .build();

            Log log = logService.add(apiKey, dto);
            System.out.println(log);
        }

        log.info("Finished database");

    }

}
