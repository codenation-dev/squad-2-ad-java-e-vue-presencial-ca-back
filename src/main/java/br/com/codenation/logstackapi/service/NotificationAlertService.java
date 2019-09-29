package br.com.codenation.logstackapi.service;

import br.com.codenation.logstackapi.model.entity.Alert;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationAlertService {

    private EmailService service;

    public void sendEmail(Alert alert) {
        String email = alert.getTrigger().getEmail();
        String subject = "[LOGSTACK ALERT] " + alert.getTrigger().getName();
        String body = getBody(alert);
        service.send(email, subject, body);
    }

    private String getBody(Alert alert) {
        StringBuilder body = new StringBuilder();
        body.append("============================================ <br>");
        body.append("<h1>[ NEW ALERT ]</h1><br>");
        body.append("============================================ <br>");
        body.append(">>> <b>Message:</b> " + alert.getTrigger().getMessage() + "<br>");
        body.append("============================================ <br>");
        body.append("<br>");
        body.append(">>> <b>Log:</b> " + alert.getLog().getTitle() + "<br>");
        body.append(">>> <b>Application:</b> " + alert.getLog().getApplication().getName() + "<br>");
        body.append(">>> <b>IP:</b> " + alert.getLog().getApplication().getIp() + "<br>");
        body.append(">>> <b>Host:</b> " + alert.getLog().getApplication().getHost() + "<br>");
        body.append(">>> <b>Environment:</b> " + alert.getLog().getApplication().getEnvironment() + "<br>");
        body.append("<br><br>");
        body.append(">>> <b>Timestamp:</b> " + alert.getLog().getDetail().getTimestamp() + "<br>");
        body.append(">>> <b>Level:</b> " + alert.getLog().getDetail().getLevel() + "<br>");
        body.append(">>> <b>Detail:</b> <br>");
        body.append(alert.getLog().getDetail().getContent());
        return body.toString();
    }

}
