package br.com.codenation.logstackapi.service;

public interface EmailService {

    void send(String to, String subject, String body);
}
