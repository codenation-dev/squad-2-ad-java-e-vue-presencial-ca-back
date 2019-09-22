package br.com.codenation.logstackapi.service.impl;

import br.com.codenation.logstackapi.model.entity.Alert;
import br.com.codenation.logstackapi.service.NotificationAlertService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationAlertServiceImpl implements NotificationAlertService {

    private EmailServiceImpl service;

    @Override
    public void sendEmail(Alert alert) {
        String email = alert.getTrigger().getEmail();
        String subject = "[ALERTA] - " + alert.getTrigger().getName();
        String body = getBody(alert);
        service.send(email, subject, body);
    }

    private String getBody(Alert alert) {
        StringBuilder body = new StringBuilder();
        body.append("============================================ <br>");
        body.append("[ NEW ALERT ]");
        body.append("============================================ <br>");
        body.append(">>> Message: " + alert.getTrigger().getMessage() + "<br>");
        body.append("============================================ <br>");
        body.append("<br><br>");
        body.append(">>> Log: " + alert.getLog().getTitle() + "<br>");
        body.append(">>> Application: " + alert.getLog().getApplication().getName() + "<br>");
        body.append(">>> Ip: " + alert.getLog().getApplication().getIp() + "<br>");
        body.append(">>> Host: " + alert.getLog().getApplication().getHost() + "<br>");
        body.append(">>> Environment: " + alert.getLog().getApplication().getEnvironment() + "<br>");
        body.append("<br><br>");
        body.append(">>> Timestamp: " + alert.getLog().getDetail().getTimestamp() + "<br>");
        body.append(">>> Level: " + alert.getLog().getDetail().getLevel() + "<br>");
        body.append(">>> Detail: <br>");
        body.append(alert.getLog().getDetail().getContent());
        return body.toString();
    }

}
