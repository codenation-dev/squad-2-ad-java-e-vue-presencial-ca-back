package br.com.codenation.logstackapi.service;

import br.com.codenation.logstackapi.model.entity.EmailMessage;

public interface EmailService {

    void sendText(EmailMessage emailMessage);

    void sendHTML(EmailMessage emailMessage);
}
