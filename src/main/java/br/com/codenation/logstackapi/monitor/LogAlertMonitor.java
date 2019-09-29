package br.com.codenation.logstackapi.monitor;

import br.com.codenation.logstackapi.mappers.TriggerSearchMapper;
import br.com.codenation.logstackapi.model.entity.Alert;
import br.com.codenation.logstackapi.model.entity.TriggerSearch;
import br.com.codenation.logstackapi.service.impl.AlertService;
import br.com.codenation.logstackapi.service.impl.LogService;
import br.com.codenation.logstackapi.service.impl.NotificationAlertService;
import br.com.codenation.logstackapi.service.impl.TriggerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class LogAlertMonitor {

    private static final Integer ITEMS_PER_CYCLE = 10;
    private static final Integer TIME_PER_CYCLE = 300000;

    private NotificationAlertService notificationAlert;
    private LogService logService;
    private TriggerService triggerService;
    private AlertService alertService;
    private TriggerSearchMapper mapper;

    @Scheduled(fixedRate = 300000)
    public void scheduledAlertNotifications() {
        verifyAlertLog();
    }

    private void verifyAlertLog() {
        log.info("### Alert Monitor ###");
        logService.findByCheckAlertNotVerified(ITEMS_PER_CYCLE).forEach(l -> {
            TriggerSearch search = mapper.map(l);
            triggerService.findByLog(search).forEach(t -> {
                Alert alert = alertService.save(Alert.builder().log(l).trigger(t).build());
                notificationAlert.sendEmail(alert);
            });
            logService.checkAlert(l.getId());
        });
    }

}
