package com.task.calling_codes.controllers;

import com.task.calling_codes.entity.Phone;
import com.task.calling_codes.entity.Response;
import com.task.calling_codes.service.IdentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeController {
    @Autowired
    private IdentificationService identificationService;

    /**
     * Main method used for identification of Telephone number
     *
     * @param phone
     * @return Response object with data to display on front end
     */
    @PostMapping("/get-country")
    public ResponseEntity<Response> findCountry(@RequestBody Phone phone) {
        return new ResponseEntity<>(identificationService.identifyCountry(phone), HttpStatus.OK);
    }
}
