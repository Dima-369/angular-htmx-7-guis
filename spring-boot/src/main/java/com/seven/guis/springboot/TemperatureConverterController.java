package com.seven.guis.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@SuppressWarnings("unused")
@Controller
public class TemperatureConverterController {

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/temperature-converter-init")
    public String init(Model model) {
        model.addAttribute("urlCelsius",  Application.baseUrl + "temperature-converter-from-celsius");
        model.addAttribute("urlFahrenheit",  Application.baseUrl + "temperature-converter-from-fahrenheit");
        return "temperature-converter-init";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/temperature-converter-from-celsius")
    public String fromCelsius(@RequestParam(value = "celsius") float celsius, Model model) {
        final double newFahrenheit = (celsius * (9.0 / 5) + 32);
        model.addAttribute("fahrenheit", formatDouble(newFahrenheit));
        model.addAttribute("url",  Application.baseUrl + "temperature-converter-from-fahrenheit");
        return "temperature-converter-fahrenheit";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/temperature-converter-from-fahrenheit")
    public String fromFahrenheit(@RequestParam(value = "fahrenheit") float fahrenheit, Model model) {
        final double newCelsius = (fahrenheit - 32) * (5 / 9.0);
        model.addAttribute("celsius", formatDouble(newCelsius));
        model.addAttribute("url",  Application.baseUrl + "temperature-converter-from-celsius");
        return "temperature-converter-celsius";
    }

    private static String formatDouble(double num) {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", otherSymbols);
        return stripTwoTrailingZeros(df.format(num));
    }

    private static String stripTwoTrailingZeros(String formattedString) {
        if (formattedString.endsWith(".00")) {
            return formattedString.substring(0, formattedString.length() - 3);
        }
        return formattedString;
    }
}