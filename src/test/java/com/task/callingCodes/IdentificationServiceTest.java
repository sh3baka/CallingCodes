package com.task.callingCodes;

import com.task.callingCodes.entity.Error;
import com.task.callingCodes.entity.Phone;
import com.task.callingCodes.entity.Response;
import com.task.callingCodes.service.IdentificationService;
import com.task.callingCodes.service.ValidationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class IdentificationServiceTest {

    @Mock
    private ValidationService validationService;
    @InjectMocks
    private IdentificationService service;


    private Phone phone;
    private Response response;
    private List<Error> errorList;
    private Error error;

    @Before
    public void setUp() {
        phone = new Phone("", "");
        response = new Response();
        errorList = new ArrayList<>();
        error = new Error();
        when(validationService.validate(phone)).thenReturn(errorList);

    }

    @Test
    public void testHappyPath() {
        phone.setTelephone("+371 2222222");
        phone.setCountry("");

        response = service.identifyCountry(phone);

        assertEquals("+3712222222", response.getTelephone());
        assertEquals("Latvia", response.getCountry());
        assertNull(response.getErrorList());

    }

    @Test
    public void testHappyPath2() {
        phone.setTelephone("+3712222222");
        phone.setCountry("");

        response = service.identifyCountry(phone);

        assertEquals("+3712222222", response.getTelephone());
        assertEquals("Latvia", response.getCountry());
        assertNull(response.getErrorList());

    }

    @Test
    public void testHappyPath3() {
        phone.setTelephone("371222 2222");
        phone.setCountry("");

        response = service.identifyCountry(phone);

        assertEquals("+3712222222", response.getTelephone());
        assertEquals("Latvia", response.getCountry());
        assertNull(response.getErrorList());

    }

    @Test
    public void testTelephoneNonNumeric() {
        phone.setTelephone("aaaaaa");
        phone.setCountry("");
        errorList.add(new Error("Phone number must be in format +1234567890 or 1234567890"));
        String errorMsg = "Phone number must be in format +1234567890 or 1234567890";

        response = service.identifyCountry(phone);

        assertEquals(1, response.getErrorList().size());
        assertEquals(errorMsg, response.getErrorList().get(0).getErrorMsg());

    }

}