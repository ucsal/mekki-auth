package br.com.mekki_auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableFeignClients
public class MekkiAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(MekkiAuthApplication.class, args);
	}

}
