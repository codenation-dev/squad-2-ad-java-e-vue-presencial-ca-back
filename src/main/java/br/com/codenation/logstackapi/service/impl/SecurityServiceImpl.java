package br.com.codenation.logstackapi.service.impl;

import br.com.codenation.logstackapi.model.entity.User;
import br.com.codenation.logstackapi.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public void autoLogin(String username, String password) {
        Optional<User> user = userService.findByEmail(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.get(), password, user.get().getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }

}
