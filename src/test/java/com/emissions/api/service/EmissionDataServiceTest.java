package com.emissions.api.service;

import com.emissions.api.model.EmissionData;
import com.emissions.api.repository.EmissionDataRepository;
import com.emissions.api.service.impl.EmissionDataServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmissionDataServiceTest {

    @Mock
    private EmissionDataRepository emissionDataRepository;

    @InjectMocks
    private EmissionDataServiceImpl emissionDataService;

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
    void testFindAll() {
        // Given
        when(emissionDataRepository.findAll()).thenReturn(Arrays.asList(emissionData));

        // When
        List<EmissionData> result = emissionDataService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Transporte", result.get(0).getSector());
        verify(emissionDataRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        // Given
        when(emissionDataRepository.findById(1L)).thenReturn(Optional.of(emissionData));

        // When
        EmissionData result = emissionDataService.findById(1L);

        // Then
        assertNotNull(result);
        assertEquals("Transporte", result.getSector());
        assertEquals(150.5, result.getCo2Emissions());
        verify(emissionDataRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        // Given
        when(emissionDataRepository.findById(999L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> emissionDataService.findById(999L));
        verify(emissionDataRepository, times(1)).findById(999L);
    }

    @Test
    void testSave() {
        // Given
        when(emissionDataRepository.save(any(EmissionData.class))).thenReturn(emissionData);

        // When
        EmissionData result = emissionDataService.save(emissionData);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Transporte", result.getSector());
        verify(emissionDataRepository, times(1)).save(emissionData);
    }

    @Test
    void testUpdate() {
        // Given
        EmissionData updatedData = new EmissionData(
                LocalDate.of(2020, 4, 1),
                "Transporte Actualizado",
                120.0,
                4,
                "MARN Actualizado"
        );

        when(emissionDataRepository.findById(1L)).thenReturn(Optional.of(emissionData));
        when(emissionDataRepository.save(any(EmissionData.class))).thenReturn(updatedData);

        // When
        EmissionData result = emissionDataService.update(1L, updatedData);

        // Then
        assertNotNull(result);
        assertEquals("Transporte Actualizado", result.getSector());
        assertEquals(120.0, result.getCo2Emissions());
        assertEquals(4, result.getRestrictionLevel());
        verify(emissionDataRepository, times(1)).findById(1L);
        verify(emissionDataRepository, times(1)).save(any(EmissionData.class));
    }

    @Test
    void testDelete() {
        // Given
        when(emissionDataRepository.findById(1L)).thenReturn(Optional.of(emissionData));
        doNothing().when(emissionDataRepository).delete(emissionData);

        // When
        emissionDataService.delete(1L);

        // Then
        verify(emissionDataRepository, times(1)).findById(1L);
        verify(emissionDataRepository, times(1)).delete(emissionData);
    }

    @Test
    void testFindBySector() {
        // Given
        when(emissionDataRepository.findBySector("Transporte")).thenReturn(Arrays.asList(emissionData));

        // When
        List<EmissionData> result = emissionDataService.findBySector("Transporte");

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Transporte", result.get(0).getSector());
        verify(emissionDataRepository, times(1)).findBySector("Transporte");
    }

    @Test
    void testFindByDateRange() {
        // Given
        LocalDate startDate = LocalDate.of(2020, 4, 1);
        LocalDate endDate = LocalDate.of(2020, 4, 30);

        when(emissionDataRepository.findByMeasurementDateBetween(startDate, endDate))
                .thenReturn(Arrays.asList(emissionData));

        // When
        List<EmissionData> result = emissionDataService.findByDateRange(startDate, endDate);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(emissionDataRepository, times(1)).findByMeasurementDateBetween(startDate, endDate);
    }
}