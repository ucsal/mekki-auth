package br.com.mekki_auth.service;

import br.com.mekki_auth.client.UserClient;
import br.com.mekki_auth.config.TokenAuthenticator;
import br.com.mekki_auth.to.UserLoginRequest;
import br.com.mekki_auth.to.UserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DefaultAuthService implements  AuthService{

    @Autowired
    private UserClient userClient;


    @Override
    public String authenticate(UserLoginRequest userLoginRequest) {
        UserTO user = userClient.authenticate(userLoginRequest).getBody();
        return TokenAuthenticator.generateToken(user);
    }
}
