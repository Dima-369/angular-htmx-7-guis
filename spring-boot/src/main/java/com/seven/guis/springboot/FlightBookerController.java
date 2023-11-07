package com.seven.guis.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
@Controller
public class FlightBookerController {

    private static final Pattern dateRegexp = Pattern.compile("^\\d{2}\\.\\d{2}\\.\\d{4}$");

    private static boolean isDateValid(String date) {
        return dateRegexp.matcher(date).matches();
    }

    private static boolean isToAfterFrom(String from, String to) throws ParseException {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        final Date fromDate = sdf.parse(from);
        final Date toDate = sdf.parse(to);
        return toDate.getTime() >= fromDate.getTime();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/flight-booker-update", method = {RequestMethod.GET, RequestMethod.POST})
    public String update(
            @RequestParam(value = "type", defaultValue = "one-way") String type,
            @RequestParam(value = "from", defaultValue = "27.03.2014") String from,
            @RequestParam(value = "to", defaultValue = "27.03.2014") String to,
            Model model) throws ParseException {

        final boolean fromValid = isDateValid(from);
        final String fromValidClass = fromValid ? null : "bg-red-400";
        final boolean toValid = isDateValid(to);
        final String toValidClass = toValid ? null : "bg-red-400";
        final boolean oneWay = type.equals("one-way");
        final boolean bookDisabled;
        if (oneWay) {
            bookDisabled = !fromValid;
        } else {
            bookDisabled = !(fromValid && toValid && isToAfterFrom(from, to));
        }

        model.addAttribute("oneWay", oneWay);
        model.addAttribute("from", from);
        model.addAttribute("fromValid", fromValid);
        model.addAttribute("toValid", toValid);
        model.addAttribute("to", to);
        model.addAttribute("toDisabled", type.equals("one-way"));
        model.addAttribute("bookDisabled", bookDisabled);
        return "flight-booker";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/flight-booker-book", method = RequestMethod.POST)
    public String book(
            @RequestParam(value = "type") String type,
            @RequestParam(value = "from") String from,
            @RequestParam(value = "to") String to,
            Model model) {

        final String alertMessage;
        if (type.equals("one-way")) {
            alertMessage = "You have booked a one-way flight on " + from;
        } else {
            alertMessage = "You have booked a return flight from " + from + " to " + to;
        }
        model.addAttribute("alertMessage", alertMessage);
        return "flight-booker-book";
    }
}