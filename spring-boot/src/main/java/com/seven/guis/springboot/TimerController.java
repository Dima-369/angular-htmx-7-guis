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

    // store those 2 fields as strings as the defaultValue expects a string
    private final static String defaultMaxTimeMs = "10000";
    private final static String defaultDurationMs = "5000";

    private static void updateModel(
            Model model, int maxTimeMs, int durationMs, long diff, int elapsedMs, long currentMs) {

        final long newElapsedMs = Math.min(elapsedMs + diff, durationMs);
        model.addAttribute("maxTimeMs", maxTimeMs);
        model.addAttribute("durationMs", durationMs);
        model.addAttribute("elapsedMs", newElapsedMs);
        model.addAttribute("lastTimeMs", currentMs);
        model.addAttribute("elapsedFormatted", TimerHelper.formatElapsedMs(elapsedMs));

        if (newElapsedMs == durationMs) {
            model.addAttribute("updateTimer", false);
            model.addAttribute("progressValueMs", maxTimeMs);
        } else {
            model.addAttribute("updateTimer", true);
            model.addAttribute("progressValueMs", (((float) elapsedMs) / durationMs) * maxTimeMs);
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/timer-init")
    public String timerInit(Model model) {
        model.addAttribute("maxTimeMs", 10000);
        model.addAttribute("durationMs", 5000);
        model.addAttribute("updateTimer", true);
        return "timer-init";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/timer", method = {RequestMethod.GET, RequestMethod.POST})
    public String timer(
            @RequestParam(value = "maxTimeMs", defaultValue = defaultMaxTimeMs) int maxTimeMs,
            @RequestParam(value = "durationMs", defaultValue = defaultDurationMs) int durationMs,
            @RequestParam(value = "elapsedMs", defaultValue = "0") int elapsedMs,
            @RequestParam(value = "lastTimeMs", defaultValue = "0") long lastTimeMs,
            Model model) {

        final long currentMs = System.currentTimeMillis();
        long diff = 0;
        if (lastTimeMs != 0) {
            diff = currentMs - lastTimeMs;
            if (diff <= 0) {
                diff = 0;
            }
        }
        updateModel(model, maxTimeMs, durationMs, diff, elapsedMs, currentMs);
        return "timer";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/timer-duration-adjust", method = RequestMethod.POST)
    public String timerTouch(
            @RequestParam(value = "maxTimeMs", defaultValue = defaultMaxTimeMs) int maxTimeMs,
            @RequestParam(value = "durationMs", defaultValue = defaultDurationMs) int durationMs,
            @RequestParam(value = "elapsedMs", defaultValue = "0") int elapsedMs,
            Model model) {

        model.addAttribute("maxTimeMs", maxTimeMs);
        model.addAttribute("durationMs", durationMs);
        model.addAttribute("elapsedMs", elapsedMs);
        model.addAttribute("lastTimeMs", System.currentTimeMillis());
        model.addAttribute("elapsedFormatted", TimerHelper.formatElapsedMs(elapsedMs));
        model.addAttribute("updateTimer", true);
        model.addAttribute("progressValueMs", (((float) elapsedMs) / durationMs) * maxTimeMs);
        return "timer";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/timer-reset", method = RequestMethod.POST)
    public String timerReset(
            @RequestParam(value = "maxTimeMs", defaultValue = defaultMaxTimeMs) int maxTimeMs,
            @RequestParam(value = "durationMs", defaultValue = defaultDurationMs) int durationMs,
            Model model) {

        updateModel(model, maxTimeMs, durationMs, 0, 0, 0);
        return "timer";
    }
}