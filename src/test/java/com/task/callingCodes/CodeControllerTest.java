package com.task.callingCodes;

import com.task.callingCodes.entity.Phone;
import com.task.callingCodes.service.IdentificationService;
import com.task.callingCodes.service.ValidationService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CodeControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private IdentificationService service;
    @Autowired
    private ValidationService validationService;

    @Before
    public void setUp() {
    }

    @Test
    public void testHappyCase() throws Exception {
        Phone phone = new Phone();
        phone.setTelephone("+371 2222222");

        mvc.perform(post("http://localhost:8080/get-country")
                .contentType(APPLICATION_JSON)
                .content(
                        "{\n" +
                                "  \"country\": null,\n" +
                                "  \"errorList\": null,\n" +
                                "  \"telephone\": \"+371 2222222\"\n" +
                                "}"
                ).characterEncoding("utf-8")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country", Matchers.equalTo("Latvia")));
    }

    @Test
    public void testHappyCaseWithoutPlus() throws Exception {
        Phone phone = new Phone();
        phone.setTelephone("+371 2222222");

        this.mvc.perform(post("http://localhost:8080/get-country")
                .contentType(APPLICATION_JSON)
                .content(
                        "{\n" +
                                "  \"country\": null,\n" +
                                "  \"errorList\": null,\n" +
                                "  \"telephone\": \"371 2222222\"\n" +
                                "}"
                ).characterEncoding("utf-8")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country", Matchers.equalTo("Latvia")));
    }

    @Test
    public void testHappyCaseWithoutPlusAndSpace() throws Exception {
        Phone phone = new Phone();
        phone.setTelephone("+371 2222222");

        this.mvc.perform(post("http://localhost:8080/get-country")
                .contentType(APPLICATION_JSON)
                .content(
                        "{\n" +
                                "  \"country\": null,\n" +
                                "  \"errorList\": null,\n" +
                                "  \"telephone\": \"3712222222\"\n" +
                                "}"
                ).characterEncoding("utf-8")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country", Matchers.equalTo("Latvia")));
    }

    @Test
    public void testTelephoneIsLetters() throws Exception {
        Phone phone = new Phone();
        phone.setTelephone("+371 2222222");

        this.mvc.perform(post("http://localhost:8080/get-country")
                .contentType(APPLICATION_JSON)
                .content(
                        "{\n" +
                                "  \"country\": null,\n" +
                                "  \"errorList\": null,\n" +
                                "  \"telephone\": \"aaaaaaaaaa\"\n" +
                                "}"
                ).characterEncoding("utf-8")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country", Matchers.equalTo(null)))
                .andExpect(jsonPath("$.errorList", Matchers.hasSize(1)));
    }

}