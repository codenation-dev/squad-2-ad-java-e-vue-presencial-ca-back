package br.com.codenation.logstackapi.service;

import com.sendgrid.Response;

public interface SendGridAPIService {

    Response sendEmail(String to, String subject, String body, String type);
}
