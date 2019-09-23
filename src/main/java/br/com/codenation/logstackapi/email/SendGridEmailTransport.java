package br.com.codenation.logstackapi.email;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class SendGridEmailTransport implements EmailTransport {

    private SendGrid sendGrid;
    private Boolean sendMailActive;

    public SendGridEmailTransport(String apiKey, Boolean sendMailActive) {
        this.sendMailActive = sendMailActive;
        this.sendGrid = new SendGrid(apiKey);
    }

    @Override
    public void send(EmailMessage emailMessage) {

        if (!sendMailActive) {
            log.info("Email não enviado. Envio de email desativado...");
            return;
        }

        Content content = new Content("text/html", emailMessage.getBody());
        Email from = new Email(emailMessage.getFrom());
        Email to = new Email(emailMessage.getTo());
        String subject = emailMessage.getSubject();
        Mail mail = new Mail(from, subject, to, content);

        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");

        try {
            request.setBody(mail.build());
            sendGrid.api(request);
            log.info("Email enviado com sucesso.");
        } catch (IOException ex) {
            log.error("Não foi possível enviar o email.");
        }

        System.out.println("Message: " + emailMessage.toString());

    }
}
