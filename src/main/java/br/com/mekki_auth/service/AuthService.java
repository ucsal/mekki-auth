package br.com.mekki_auth.service;

import br.com.mekki_auth.to.UserLoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    String authenticate(UserLoginRequest userLoginRequest);
}

