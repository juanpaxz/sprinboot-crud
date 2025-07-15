package com.juan.curso.springboot.app.sprinbootcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:messages.properties")
public class SprinbootCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SprinbootCrudApplication.class, args);
    }

}
