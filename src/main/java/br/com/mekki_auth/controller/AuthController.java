package br.com.mekki_auth.controller;

import br.com.mekki_auth.config.TokenAuthenticator;
import br.com.mekki_auth.service.AuthService;
import br.com.mekki_auth.to.UserLoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody UserLoginRequest userLoginRequest){
         String token = authService.authenticate(userLoginRequest);
        return ResponseEntity.ok().header(TokenAuthenticator.HEADER_STRING,token).body(null);
    }

}
