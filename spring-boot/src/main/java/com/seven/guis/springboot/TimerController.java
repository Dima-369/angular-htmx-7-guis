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

    // store those 2 fields as strings as defaultValue for @RequestParam expects a string
    private final static String defaultMaxTimeMs = "10000";
    private final static String defaultDurationMs = "5000";

    /**
     * @return a string like "0.1s" rounded to 1 decimal place
     */
    private static String formatElapsed(int elapsedMs) {
        double elapsedSeconds = (double) elapsedMs / 1000;
        return String.format("%.1fs", elapsedSeconds);
    }

    private static void updateModel(
            Model model, int maxTimeMs, int durationMs, long diff, int elapsedMs,
            long currentMs) {

        final float newProgressValueMs = (((float) elapsedMs) / durationMs) * maxTimeMs;
        final boolean needsTimerUpdates = (int)newProgressValueMs != maxTimeMs;
        model.addAttribute("updateTimer", needsTimerUpdates);
        System.out.println((int)newProgressValueMs != maxTimeMs);

        model.addAttribute("maxTimeMs", maxTimeMs);
        model.addAttribute("durationMs", durationMs);
        model.addAttribute("elapsedMs", needsTimerUpdates ? Math.min(elapsedMs + diff, durationMs) : 0);
        model.addAttribute("lastTimeMs", needsTimerUpdates ? currentMs : 0);
        model.addAttribute("progressValueMs", newProgressValueMs);
        model.addAttribute("elapsedFormatted", formatElapsed(elapsedMs));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/timer-init")
    public String timerInit(Model model) {
        model.addAttribute("maxTimeMs", 10000);
        model.addAttribute("durationMs", 5000);
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
    @RequestMapping(value = "/timer-reset", method = RequestMethod.POST)
    public String timerReset(
            @RequestParam(value = "maxTimeMs", defaultValue = defaultMaxTimeMs) int maxTimeMs,
            @RequestParam(value = "durationMs", defaultValue = defaultDurationMs) int durationMs,
            Model model) {

        updateModel(model, maxTimeMs, durationMs, 0, 0, 0);
        return "timer";
    }
}