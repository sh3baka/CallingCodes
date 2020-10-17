package com.task.callingCodes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdentificationService {
    private final WikiTable wikiTable = new WikiTable();
    @Autowired
    private Validator validator;



    public Response identifyCountry(Phone phone) {
        List<Error> errorList = validator.validate(phone);

        if (errorList.isEmpty()) {
            Phone updatedPhone = unifyPhoneNr(phone);
            wikiTable.getMapOfCodes().forEach((k, v) -> {
                if (updatedPhone.getTelephone().startsWith(k))
                    updatedPhone.setCountry(v);
            });
            return new Response(updatedPhone.getCountry(),updatedPhone.getTelephone(), null);
        }
        return new Response(null,null, errorList);
    }

    private Phone unifyPhoneNr(Phone phone) {
        if (phone.getTelephone().startsWith("+")) {
            phone.setTelephone(phone.getTelephone().replace(" ", ""));
        } else {
            phone.setTelephone("+" + phone.getTelephone().replace(" ", ""));
        }
        return phone;
    }
}
