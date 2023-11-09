package com.seven.guis.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@SuppressWarnings("unused")
@Controller
public class TimerController {

    // store those 2 fields as strings as defaultValue for @RequestParam expects a string
    private final static String defaultMaxTimeMs = "10000";
    private final static String defaultDurationMs = "5000";

    private static String formatDouble(double num) {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.0", otherSymbols);
        return stripTwoTrailingZeros(df.format(num));
    }

    private static String stripTwoTrailingZeros(String formattedString) {
        if (formattedString.endsWith(".0")) {
            return formattedString.substring(0, formattedString.length() - 2);
        }
        return formattedString;
    }

    /**
     * @return a string like "0.1s" rounded to 1 decimal place
     */
    private static String formatElapsed(int elapsedMs) {
        double elapsedSeconds = (double) elapsedMs / 1000;
        return String.format("%.1fs", elapsedSeconds);
    }

    private static void updateModel(Model model, int maxTimeMs, int durationMs, int tick) {
        final int tickMs = tick * 100;
        if (tickMs >= durationMs) {
            model.addAttribute("progressValueMs", 10000);
            model.addAttribute("updateTimer", false);
        } else {
            model.addAttribute("progressValueMs", ((float)tickMs / durationMs) * maxTimeMs);
            model.addAttribute("updateTimer", true);
        }

//        final float newProgressValueMs = (((float) (tick * 100)) / durationMs) * maxTimeMs;
//        final boolean needsTimerUpdates = (int)newProgressValueMs != maxTimeMs;
//        model.addAttribute("updateTimer", needsTimerUpdates);
//        model.addAttribute("updateTimer", true);
        System.out.println("tick: " + tick + ", durationMs: " + durationMs + ", maxTimeMs" + maxTimeMs);

        model.addAttribute("maxTimeMs", maxTimeMs);
        model.addAttribute("durationMs", durationMs);
        model.addAttribute("elapsedMs", Math.min(tick * 100, durationMs));
        model.addAttribute("tick", tick + 1);

        model.addAttribute("elapsedFormatted", formatElapsed(tick * 100));
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
            @RequestParam(value = "tick", defaultValue = "0") int tick,
            Model model) {

        // todo dynamic tick value
        updateModel(model, maxTimeMs, durationMs, tick);
        return "timer";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/timer-reset", method = RequestMethod.POST)
    public String timerReset(
            @RequestParam(value = "maxTimeMs", defaultValue = defaultMaxTimeMs) int maxTimeMs,
            @RequestParam(value = "durationMs", defaultValue = defaultDurationMs) int durationMs,
            @RequestParam(value = "tick", defaultValue = "0") int tick,
            Model model) {

        updateModel(model, maxTimeMs, durationMs, 0);
        return "timer";
    }
}