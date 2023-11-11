package com.seven.guis.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@SpringBootApplication
@RestController
public class Application {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);

        // check port environment since render.com expects port 10000 by default
        String port = System.getenv("PORT");
        if (port == null) {
           port = "10000" ;
        }
        app.setDefaultProperties(Collections.singletonMap("server.port", port));

        app.run(args);
    }
}
