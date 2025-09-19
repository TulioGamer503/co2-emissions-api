package com.emissions.api.integration;

import com.emissions.api.model.EmissionData;
import com.emissions.api.repository.EmissionDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class EmissionDataIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmissionDataRepository emissionDataRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateAndRetrieveEmission() throws Exception {
        // Create
        EmissionData emissionData = new EmissionData(
                LocalDate.of(2020, 4, 1),
                "Transporte",
                150.5,
                3,
                "MARN"
        );

        mockMvc.perform(post("/api/emissions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emissionData)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.sector").value("Transporte"));

        // Retrieve all
        mockMvc.perform(get("/api/emissions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].sector").value("Transporte"));
    }

    @Test
    void testCreateUpdateDeleteFlow() throws Exception {
        // Create
        EmissionData emissionData = new EmissionData(
                LocalDate.of(2020, 4, 1),
                "Transporte",
                150.5,
                3,
                "MARN"
        );

        String response = mockMvc.perform(post("/api/emissions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emissionData)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        EmissionData created = objectMapper.readValue(response, EmissionData.class);

        // Update
        created.setSector("Transporte Actualizado");
        created.setCo2Emissions(120.0);

        mockMvc.perform(put("/api/emissions/" + created.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(created)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sector").value("Transporte Actualizado"));

        // Delete
        mockMvc.perform(delete("/api/emissions/" + created.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // Verify deleted
        mockMvc.perform(get("/api/emissions/" + created.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}