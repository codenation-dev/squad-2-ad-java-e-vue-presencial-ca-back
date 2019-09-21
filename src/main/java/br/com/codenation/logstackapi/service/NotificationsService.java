package br.com.codenation.logstackapi.service;

import br.com.codenation.logstackapi.model.entity.EmailMessage;

public interface NotificationsService {

    void sendEmail(EmailMessage emailMessage);

}
