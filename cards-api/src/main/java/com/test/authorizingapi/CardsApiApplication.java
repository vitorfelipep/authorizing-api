package com.test.authorizingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class CardsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardsApiApplication.class, args);
    }

}
