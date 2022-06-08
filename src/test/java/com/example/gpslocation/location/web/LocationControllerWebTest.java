package com.example.gpslocation.location.web;

import com.example.gpslocation.location.application.port.CreateLocationUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest({LocationController.class})
class LocationControllerWebTest {

    @MockBean
    CreateLocationUseCase service;

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldReturn400WhenDeviceIdIsNegative() throws Exception {
        //GIVEN
        String json = givenLocation();

        //WHEN
        mockMvc
                .perform(post("/api/locations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
        //THEN
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenDeviceIdIsNull() throws Exception {
        //GIVEN
        String json = givenLocation();

        //WHEN
        mockMvc
                .perform(post("/api/locations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
        //THEN
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenLatitudeIsTooShort() throws Exception {
        //GIVEN
        String json = givenLocation();

        //WHEN
        mockMvc
                .perform(post("/api/locations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                //THEN
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenLongitudeIsTooShort() throws Exception {
        //GIVEN
        String json = givenLocation();

        //WHEN
        mockMvc
                .perform(post("/api/locations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                //THEN
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private String givenLocation() {
        return givenLocation();
    }

}