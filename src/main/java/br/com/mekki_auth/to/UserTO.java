package br.com.mekki_auth.to;

import lombok.Data;

import java.util.List;

@Data
public class UserTO {

    private String Username;

    private String email;

    private String school;

    private List<String> roles;
}
