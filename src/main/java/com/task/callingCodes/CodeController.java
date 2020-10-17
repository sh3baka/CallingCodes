package com.task.callingCodes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeController {
    @Autowired
    private IdentificationService identificationService;

    @PostMapping("/get-country")
    public Response findCountry(@RequestBody Phone phone) {
        return identificationService.identifyCountry(phone);
    }
}
