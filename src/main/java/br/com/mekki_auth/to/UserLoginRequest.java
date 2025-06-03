package br.com.mekki_auth.to;


import lombok.Data;

@Data
public class UserLoginRequest {

    private String email;

    private String password;
}
