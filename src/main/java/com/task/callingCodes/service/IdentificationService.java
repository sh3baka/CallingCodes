package com.task.callingCodes.service;

import com.task.callingCodes.entity.Error;
import com.task.callingCodes.entity.Phone;
import com.task.callingCodes.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdentificationService {
    private final WikiTable wikiTable = new WikiTable();
    @Autowired
    private ValidationService validationService;

    /**
     * Creating response object containing Telephone number , Country it belong to , and list of errors if present
     *
     * @param phone
     * @return Response object for frontend
     */
    public Response identifyCountry(Phone phone) {
        List<Error> errorList = validationService.validate(phone);

        if (errorList.isEmpty()) {
            Phone updatedPhone = unifyPhoneNr(phone);
            wikiTable.getMapOfCodes().forEach((k, v) -> {
                if (updatedPhone.getTelephone().startsWith(k))
                    updatedPhone.setCountry(v);
            });
            return new Response(updatedPhone.getCountry(), updatedPhone.getTelephone(), null);
        }
        return new Response(null, null, errorList);
    }

    /**
     * Remove all spaces and add plus sign if it was not added by user
     *
     * @param phone
     * @return Formatted phone number in format of +000000000
     */
    private Phone unifyPhoneNr(Phone phone) {
        if (phone.getTelephone().startsWith("+")) {
            phone.setTelephone(phone.getTelephone().replace(" ", ""));
        } else {
            phone.setTelephone("+" + phone.getTelephone().replace(" ", ""));
        }
        return phone;
    }
}
