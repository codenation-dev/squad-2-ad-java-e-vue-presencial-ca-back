package br.com.codenation.logstackapi.monitor;

import br.com.codenation.logstackapi.model.entity.Alert;
import br.com.codenation.logstackapi.service.impl.AlertServiceImpl;
import br.com.codenation.logstackapi.service.impl.LogServiceImpl;
import br.com.codenation.logstackapi.service.impl.NotificationAlertServiceImpl;
import br.com.codenation.logstackapi.service.impl.TriggerServiceImpl;
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

    private NotificationAlertServiceImpl notificationAlert;
    private LogServiceImpl logService;
    private TriggerServiceImpl triggerService;
    private AlertServiceImpl alertService;

    @Scheduled(fixedRate = 300000)
    public void scheduledAlertNotifications() {
        verifyAlertLog();
    }

    private void verifyAlertLog() {
        log.info("### Alert Monitor ###");
        logService.findByCheckAlertNotVerified(ITEMS_PER_CYCLE).forEach(l -> {
            triggerService.findByLog(l.getApplication().getName(), l.getApplication().getEnvironment(), l.getDetail().getLevel()).forEach(t -> {
                Alert alert = Alert.builder().log(l).trigger(t).build();
                alert = alertService.save(alert);
                notificationAlert.sendEmail(alert);
            });
            logService.checkAlert(l.getId());
        });
    }

}
