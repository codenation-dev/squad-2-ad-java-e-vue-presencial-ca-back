package br.com.codenation.logstackapi.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmailTransportFactory {

    private final Environment environment;

    @Autowired
    public EmailTransportFactory(Environment environment) {
        this.environment = environment;
    }

    public EmailTransport build() {

        Boolean sendMailActive = Optional.ofNullable(Boolean.valueOf(environment.getProperty("app.sendgrid.active")))
                .orElse(false);

        String sendGridKey = Optional.ofNullable(environment.getProperty("app.sendgrid.key"))
                .orElseThrow(() -> new RuntimeException("Can't send email: SendGrid API key not configured."));

        return new SendGridEmailTransport(sendGridKey, sendMailActive);
    }

}
