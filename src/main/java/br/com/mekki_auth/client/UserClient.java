package br.com.mekki_auth.client;

import br.com.mekki_auth.to.UserLoginRequest;
import br.com.mekki_auth.to.UserTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Userlient", url = "http://localhost:8082/user")
public interface UserClient {

    @PostMapping("/authenticate")
    ResponseEntity<UserTO> authenticate(@RequestBody UserLoginRequest loginTo);

}
