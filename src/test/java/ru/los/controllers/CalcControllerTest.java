package ru.los.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.los.data.controllers.CalcController;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CalcControllerTest {

    @InjectMocks
    private CalcController calcController;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(calcController)
                .build();
    }

    @Test
    public void shouldGetSum() throws Exception {
        this.mockMvc
                .perform(
                        get("/add?a=5&b=12"))
                .andExpect(content().string("17"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetDivision() throws Exception {
        this.mockMvc
                .perform(
                        get("/divide?a=10&b=5"))
                .andExpect(content().string("2"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetMultiplication() throws Exception {
        this.mockMvc
                .perform(
                        get("/multiple?a=10&b=5"))
                .andExpect(content().string("50"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetSubstraction() throws Exception {
        this.mockMvc
                .perform(
                        get("/substract?a=1000&b=5"))
                .andExpect(content().string("995"))
                .andExpect(status().isOk());
    }
}
