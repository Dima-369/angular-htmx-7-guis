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

    private static String formatElapsed(int elapsedMs) {
        double elapsedSeconds = (double) elapsedMs / 1000;
        return String.format("%.1fs", elapsedSeconds);
    }

    // todo function to set models instead of copy/paste?

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/timer", method = {RequestMethod.GET, RequestMethod.POST})
    public String timer(
            @RequestParam(value = "maxTimeMs", defaultValue = "10000") int maxTimeMs,
            @RequestParam(value = "durationMs", defaultValue = "5000") int durationMs,
            @RequestParam(value = "elapsedMs", defaultValue = "0") int elapsedMs,
            @RequestParam(value = "progressValueMs", defaultValue = "0") int progressValueMs,
            Model model) {

        // todo how to do the last timestamp calculation?
        model.addAttribute("maxTimeMs", maxTimeMs);
        model.addAttribute("durationMs", durationMs);
        model.addAttribute("elapsedMs", elapsedMs);
        model.addAttribute("progressValueMs", progressValueMs);
        model.addAttribute("elapsedFormatted", formatElapsed(elapsedMs));
        return "timer";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/timer-reset", method = RequestMethod.POST)
    public String timerReset(
            @RequestParam(value = "maxTimeMs", defaultValue = "10000") int maxTimeMs,
            @RequestParam(value = "durationMs", defaultValue = "5000") int durationMs,
            @RequestParam(value = "progressValueMs", defaultValue = "0") int progressValueMs,
            Model model) {

        // todo how to do the last timestamp calculation?
        model.addAttribute("maxTimeMs", maxTimeMs);
        model.addAttribute("durationMs", durationMs);
        model.addAttribute("progressValueMs", progressValueMs);

        final int elapsedMs = 0;
        model.addAttribute("elapsedMs", elapsedMs);
        model.addAttribute("elapsedFormatted", formatElapsed(elapsedMs));

        return "timer";
    }

    // todo how to do the reset button?
}