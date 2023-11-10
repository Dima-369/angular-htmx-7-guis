package com.seven.guis.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@SuppressWarnings("unused")
@Controller
public class CrudController {

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/crud", method = {RequestMethod.GET, RequestMethod.POST})
    public String crud(
            @RequestParam(value = "lastTimeMs", defaultValue = "0") long lastTimeMs,
            Model model) {

        return "crud";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/crud-create", method = RequestMethod.POST)
    public String crudCreate(Model model) {
        return "crud";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/crud-update", method = RequestMethod.POST)
    public String crudUpdate(Model model) {
        return "crud";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/crud-delete", method = RequestMethod.POST)
    public String crudDelete(Model model) {
        return "crud";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/crud-unset", method = RequestMethod.POST)
    public String crudUnset(Model model) {
        return "crud";
    }

    // filter, name, surname, selected
}