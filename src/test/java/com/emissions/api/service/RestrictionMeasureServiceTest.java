package com.emissions.api.service;

import com.emissions.api.model.RestrictionMeasure;
import com.emissions.api.repository.RestrictionMeasureRepository;
import com.emissions.api.service.impl.RestrictionMeasureServiceImpl;
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
class RestrictionMeasureServiceTest {

    @Mock
    private RestrictionMeasureRepository restrictionMeasureRepository;

    @InjectMocks
    private RestrictionMeasureServiceImpl restrictionMeasureService;

    private RestrictionMeasure restrictionMeasure;

    @BeforeEach
    void setUp() {
        restrictionMeasure = new RestrictionMeasure(
                "Confinamiento",
                "Restricción de movilidad",
                LocalDate.of(2020, 3, 20),
                LocalDate.of(2020, 5, 15),
                5,
                "Transporte"
        );
        restrictionMeasure.setId(1L);
    }

    @Test
    void testFindAll() {
        when(restrictionMeasureRepository.findAll()).thenReturn(Arrays.asList(restrictionMeasure));

        List<RestrictionMeasure> result = restrictionMeasureService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Confinamiento", result.get(0).getMeasureType());
        verify(restrictionMeasureRepository, times(1)).findAll();
    }

    @Test
    void testFindByAffectedSector() {
        when(restrictionMeasureRepository.findByAffectedSector("Transporte"))
                .thenReturn(Arrays.asList(restrictionMeasure));

        List<RestrictionMeasure> result = restrictionMeasureService.findByAffectedSector("Transporte");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Transporte", result.get(0).getAffectedSector());
        verify(restrictionMeasureRepository, times(1)).findByAffectedSector("Transporte");
    }

    @Test
    void testFindByIntensityLevel() {
        when(restrictionMeasureRepository.findByIntensityLevel(5))
                .thenReturn(Arrays.asList(restrictionMeasure));

        List<RestrictionMeasure> result = restrictionMeasureService.findByIntensityLevel(5);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(5, result.get(0).getIntensityLevel());
        verify(restrictionMeasureRepository, times(1)).findByIntensityLevel(5);
    }
}