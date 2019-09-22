package br.com.codenation.logstackapi.service;

import br.com.codenation.logstackapi.model.entity.Alert;

public interface NotificationsAlertService {

    void sendEmail(Alert alert);

}
