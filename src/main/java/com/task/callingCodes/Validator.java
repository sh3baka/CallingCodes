package com.task.callingCodes;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Validator {

    public List<Error> validate(Phone phone) {
        List<Error> errors = new ArrayList<>();
        shouldMatchRegex(phone.getTelephone()).ifPresent(errors::add);

        return errors;
    }
    private Optional<Error> shouldMatchRegex(String phoneNumber) {
        if (!phoneNumber.matches("^([+\\d].*)?\\d$")) {
            return Optional.of(new Error( "Phone number must be in format +1234567890 or 1234567890"));
        } else {
            return Optional.empty();
        }
    }
}
