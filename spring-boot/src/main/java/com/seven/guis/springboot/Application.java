package com.seven.guis.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class Application {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8083"));
		app.run(args);
	}

//	@GetMapping("/counter")
//	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
//		return String.format("%s!", name);
//		Map<String, Object> model;
//		model.put("title", "Hello Vue!");
//		return new ModelAndView("counter.html", model);
//	}
}