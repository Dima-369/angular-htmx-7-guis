package com.seven.guis.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@SuppressWarnings("unused")
@Controller
public class InfoController {

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/info")
    public String info(Model model) {
        return "info";
    }
}