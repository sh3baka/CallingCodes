package com.task.callingCodes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {
    private WikiTable wikiTable = new WikiTable();

    @GetMapping("/")
    public String landingPage() {

    return "HelloWorld";
    }
}