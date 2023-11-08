package com.seven.guis.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@SuppressWarnings("unused")
@Controller
public class TimerController {

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/timer", method = {RequestMethod.GET, RequestMethod.POST})
    public String timer(
            @RequestParam(value = "maxTimeMs", defaultValue = "10000") int maxTimeMs,
            @RequestParam(value = "durationMs", defaultValue = "5000") int durationMs,
            @RequestParam(value = "elapsedMs", defaultValue = "0") int elapsedMs,
            @RequestParam(value = "totalTime", defaultValue = "27.03.2014") String to,
            Model model) {

        // todo how to do the timestamps?
        return "oimero";
    }
}