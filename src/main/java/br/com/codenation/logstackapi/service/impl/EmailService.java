package br.com.codenation.logstackapi.service.impl;

import br.com.codenation.logstackapi.email.EmailMessage;
import br.com.codenation.logstackapi.email.EmailTransport;
import br.com.codenation.logstackapi.email.EmailTransportFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final EmailTransport emailTransport;
    private String sender;

    @Autowired
    public EmailService(EmailTransportFactory transportFactory, @Value("${app.sendgrid.sender:no-reply@gmail.com}") String sender) {
        this.emailTransport = transportFactory.build();
        this.sender = sender;
    }

    public void send(String to, String subject, String body) {
        EmailMessage emailMessage = EmailMessage.builder()
                .from(sender)
                .to(to)
                .subject(subject)
                .body(body)
                .build();
        emailTransport.send(emailMessage);
    }


}
