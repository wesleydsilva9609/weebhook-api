package com.lojavirtual.web_hooks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.lojavirtual.web_hooks")
public class WebHooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebHooksApplication.class, args);
	}

}
