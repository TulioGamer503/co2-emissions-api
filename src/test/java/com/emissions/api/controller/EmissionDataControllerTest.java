package com.emissions.api.controller;

import com.emissions.api.model.EmissionData;
import com.emissions.api.service.EmissionDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmissionDataController.class)
class EmissionDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmissionDataService emissionDataService;

    @Autowired
    private ObjectMapper objectMapper;

    private EmissionData emissionData;

    @BeforeEach
    void setUp() {
        emissionData = new EmissionData(
                LocalDate.of(2020, 4, 1),
                "Transporte",
                150.5,
                3,
                "MARN"
        );
        emissionData.setId(1L);
    }

    @Test
    void testGetAllEmissions() throws Exception {
        // Given
        List<EmissionData> emissions = Arrays.asList(emissionData);
        when(emissionDataService.findAll()).thenReturn(emissions);

        // When & Then
        mockMvc.perform(get("/api/emissions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].sector").value("Transporte"))
                .andExpect(jsonPath("$[0].co2Emissions").value(150.5));

        verify(emissionDataService, times(1)).findAll();
    }

    @Test
    void testGetEmissionById() throws Exception {
        // Given
        when(emissionDataService.findById(1L)).thenReturn(emissionData);

        // When & Then
        mockMvc.perform(get("/api/emissions/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.sector").value("Transporte"));

        verify(emissionDataService, times(1)).findById(1L);
    }

    @Test
    void testCreateEmission() throws Exception {
        // Given
        when(emissionDataService.save(any(EmissionData.class))).thenReturn(emissionData);

        // When & Then
        mockMvc.perform(post("/api/emissions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emissionData)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.sector").value("Transporte"));

        verify(emissionDataService, times(1)).save(any(EmissionData.class));
    }

    @Test
    void testUpdateEmission() throws Exception {
        // Given
        when(emissionDataService.update(eq(1L), any(EmissionData.class))).thenReturn(emissionData);

        // When & Then
        mockMvc.perform(put("/api/emissions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emissionData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sector").value("Transporte"));

        verify(emissionDataService, times(1)).update(eq(1L), any(EmissionData.class));
    }

    @Test
    void testDeleteEmission() throws Exception {
        // Given
        doNothing().when(emissionDataService).delete(1L);

        // When & Then
        mockMvc.perform(delete("/api/emissions/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(emissionDataService, times(1)).delete(1L);
    }

    @Test
    void testGetEmissionsBySector() throws Exception {
        // Given
        List<EmissionData> emissions = Arrays.asList(emissionData);
        when(emissionDataService.findBySector("Transporte")).thenReturn(emissions);

        // When & Then
        mockMvc.perform(get("/api/emissions/sector/Transporte")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].sector").value("Transporte"));

        verify(emissionDataService, times(1)).findBySector("Transporte");
    }

    @Test
    void testCreateEmission_ValidationError() throws Exception {
        // Given - EmissionData without required fields
        EmissionData invalidEmission = new EmissionData();

        // When & Then
        mockMvc.perform(post("/api/emissions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidEmission)))
                .andExpect(status().isBadRequest());

        verify(emissionDataService, never()).save(any(EmissionData.class));
    }
}