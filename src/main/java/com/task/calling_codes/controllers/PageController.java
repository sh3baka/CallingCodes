package com.task.calling_codes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    /**
     * Method to display html page
     *
     * @return landing Page (index.html)
     */
    @RequestMapping("/")
    public String landingPage() {
        return "index.html";
    }
}