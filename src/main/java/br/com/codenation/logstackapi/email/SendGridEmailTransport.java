package br.com.codenation.logstackapi.email;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

public class SendGridEmailTransport implements EmailTransport {

    private SendGrid sendGrid;

    public SendGridEmailTransport(String apiKey) {
        sendGrid = new SendGrid(apiKey);
    }

    @Override
    public void send(EmailMessage emailMessage) {

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
            System.out.println("Email enviado com sucesso.");
        } catch (IOException ex) {
            System.out.println("Não foi possível enviar o email.");
        }

        System.out.println("Message: " + emailMessage.toString());

    }
}
