package com.seven.guis.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@SpringBootApplication
@RestController
public class Application {

    /**
     * Ending in /. Setup in main() with the port.
     */
    public static String baseUrl;

    public static void main(String[] args) {
        // check port environment since render.com expects port 10000 by default
        String port = System.getenv("PORT");
        if (port == null) {
            port = "10000";
        }

        String deployOnRender = System.getenv("DEPLOY_ON_RENDER");
        if (deployOnRender == null) {
            baseUrl = "http://localhost:" + port + "/";
        } else {
            baseUrl = "https://java-7-guis.onrender.com/";
        }

        SpringApplication app = new SpringApplication(Application.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", port));
        app.run(args);
    }
}
