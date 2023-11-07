package com.seven.guis.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@SuppressWarnings("unused")
@Controller
public class CounterController {

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/counter", method = RequestMethod.POST)
    public String counter(@RequestParam(value = "counter", defaultValue = "-1") int counter, Model model) {
        model.addAttribute("counter", counter + 1);
        return "counter";
    }
}