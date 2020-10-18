package com.task.calling_codes.service;

import com.task.calling_codes.entity.Error;
import com.task.calling_codes.entity.Phone;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ValidationService {

    /**
     * Method to validate entered phone math criteria
     *
     * @param phone
     * @return List of Errors
     */
    public List<Error> validate(Phone phone) {
        List<Error> errors = new ArrayList<>();
        shouldMatchRegex(phone.getTelephone()).ifPresent(errors::add);
        shouldMatchLength(phone.getTelephone()).ifPresent(errors::add);

        return errors;
    }

    /**
     * Helper method to check that telephone match Regex
     *
     * @param phoneNumber
     * @return Optional<Error>
     */
    private Optional<Error> shouldMatchRegex(String phoneNumber) {
        if (!phoneNumber.matches("^([+\\d].*)?\\d$")) {
            return Optional.of(new Error("Phone number must be in format +1234567890 or 1234567890"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Error> shouldMatchLength(String phoneNumber) {
        if (phoneNumber.length() < 6 || phoneNumber.length() > 16) {
            return Optional.of(new Error("Phone number must be from 6 to 16 length"));
        } else {
            return Optional.empty();
        }
    }
}
