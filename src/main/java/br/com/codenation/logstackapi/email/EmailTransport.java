package br.com.codenation.logstackapi.email;

public interface EmailTransport {

    void send(EmailMessage emailMessage);

}

