package com.task.calling_codes;

import com.task.calling_codes.entity.Error;
import com.task.calling_codes.entity.Phone;
import com.task.calling_codes.service.ValidationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class ValidationServiceTest {

    private ValidationService validationService;

    @Before
    public void setUp() {
        validationService = new ValidationService();
    }

    @Test
    public void testValidatorHappyCase() {
        Phone phone = new Phone();
        phone.setTelephone("+371123456");

        List<Error> errors = validationService.validate(phone);

        assertEquals(0, errors.size());
    }

    @Test
    public void testValidatorHappyCaseWithoutPlusSign() {
        Phone phone = new Phone();
        phone.setTelephone("371123456");

        List<Error> errors = validationService.validate(phone);

        assertEquals(0, errors.size());
    }

    @Test
    public void testValidatorHappyCaseWithoutPlusSignAndWithSpace() {
        Phone phone = new Phone();
        phone.setTelephone("3711 23456");

        List<Error> errors = validationService.validate(phone);

        assertEquals(0, errors.size());
    }

    @Test
    public void testValidatorHappyCaseWithPlusSignAndWithSpace() {
        Phone phone = new Phone();
        phone.setTelephone("+3711 23456");

        List<Error> errors = validationService.validate(phone);

        assertEquals(0, errors.size());
    }

    @Test
    public void testValidatorLettersInInput() {
        Phone phone = new Phone();
        String errorMessage = "Phone number must be in format +1234567890 or 1234567890";
        phone.setTelephone("aaaaaaa");

        List<Error> errors = validationService.validate(phone);

        assertEquals(1, errors.size());
        assertEquals(errorMessage, errors.get(0).getErrorMsg());
    }

    @Test
    public void testValidatorLettersInInputAndPlusSign() {
        Phone phone = new Phone();
        String errorMessage = "Phone number must be in format +1234567890 or 1234567890";
        phone.setTelephone("+aaaaaaa");

        List<Error> errors = validationService.validate(phone);

        assertEquals(1, errors.size());
        assertEquals(errorMessage, errors.get(0).getErrorMsg());
    }

    @Test
    public void testValidatorLettersInInputAndPlusSignAndSpace() {
        Phone phone = new Phone();
        String errorMessage = "Phone number must be in format +1234567890 or 1234567890";
        phone.setTelephone("+aaaa aaa");

        List<Error> errors = validationService.validate(phone);

        assertEquals(1, errors.size());
        assertEquals(errorMessage, errors.get(0).getErrorMsg());
    }

    @Test
    public void testValidatorWithSigns() {
        Phone phone = new Phone();
        String errorMessage = "Phone number must be in format +1234567890 or 1234567890";
        phone.setTelephone("+*&^%$%$#");

        List<Error> errors = validationService.validate(phone);

        assertEquals(1, errors.size());
        assertEquals(errorMessage, errors.get(0).getErrorMsg());
    }

    @Test
    public void testValidatorWithSignsAndSpace() {
        Phone phone = new Phone();
        String errorMessage = "Phone number must be in format +1234567890 or 1234567890";
        phone.setTelephone("+*&^% $%$#");

        List<Error> errors = validationService.validate(phone);

        assertEquals(1, errors.size());
        assertEquals(errorMessage, errors.get(0).getErrorMsg());
    }

    @Test
    public void testValidatorWithSignsAndSpaceWithoutPlus() {
        Phone phone = new Phone();
        String errorMessage = "Phone number must be in format +1234567890 or 1234567890";
        phone.setTelephone("*&^% $%$#");

        List<Error> errors = validationService.validate(phone);

        assertEquals(1, errors.size());
        assertEquals(errorMessage, errors.get(0).getErrorMsg());
    }

    @Test
    public void testValidatorWithCorrectLength() {
        Phone phone = new Phone();
        phone.setTelephone("+3711111111");

        List<Error> errors = validationService.validate(phone);

        assertEquals(0, errors.size());
    }

    @Test
    public void testValidatorWithIncorrectLength() {
        Phone phone = new Phone();
        String errorMessage = "Phone number must be from 6 to 16 length";
        phone.setTelephone("+3711111111111111");

        List<Error> errors = validationService.validate(phone);

        assertEquals(1, errors.size());
        assertEquals(errorMessage, errors.get(0).getErrorMsg());
    }

    @Test
    public void testValidatorWithIncorrectLength2() {
        Phone phone = new Phone();
        String errorMessage = "Phone number must be from 6 to 16 length";
        phone.setTelephone("+371");

        List<Error> errors = validationService.validate(phone);

        assertEquals(1, errors.size());
        assertEquals(errorMessage, errors.get(0).getErrorMsg());
    }

}