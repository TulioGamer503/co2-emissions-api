package com.emissions.api.repository;

import com.emissions.api.model.EmissionData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmissionDataRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmissionDataRepository emissionDataRepository;

    @Test
    void testFindBySector() {
        // Given
        EmissionData emission1 = new EmissionData(
                LocalDate.of(2020, 4, 1), "Transporte", 150.5, 3, "MARN");
        EmissionData emission2 = new EmissionData(
                LocalDate.of(2020, 4, 2), "Industria", 250.0, 2, "MARN");

        entityManager.persist(emission1);
        entityManager.persist(emission2);
        entityManager.flush();

        // When
        List<EmissionData> transportEmissions = emissionDataRepository.findBySector("Transporte");
        List<EmissionData> industryEmissions = emissionDataRepository.findBySector("Industria");

        // Then
        assertEquals(1, transportEmissions.size());
        assertEquals("Transporte", transportEmissions.get(0).getSector());

        assertEquals(1, industryEmissions.size());
        assertEquals("Industria", industryEmissions.get(0).getSector());
    }

    @Test
    void testFindByMeasurementDateBetween() {
        // Given
        EmissionData emission1 = new EmissionData(
                LocalDate.of(2020, 4, 1), "Transporte", 150.5, 3, "MARN");
        EmissionData emission2 = new EmissionData(
                LocalDate.of(2020, 5, 1), "Transporte", 160.0, 2, "MARN");

        entityManager.persist(emission1);
        entityManager.persist(emission2);
        entityManager.flush();

        // When
        LocalDate startDate = LocalDate.of(2020, 4, 1);
        LocalDate endDate = LocalDate.of(2020, 4, 30);
        List<EmissionData> result = emissionDataRepository.findByMeasurementDateBetween(startDate, endDate);

        // Then
        assertEquals(1, result.size());
        assertEquals(LocalDate.of(2020, 4, 1), result.get(0).getMeasurementDate());
    }

    @Test
    void testFindBySectorAndMeasurementDateBetween() {
        // Given
        EmissionData emission1 = new EmissionData(
                LocalDate.of(2020, 4, 1), "Transporte", 150.5, 3, "MARN");
        EmissionData emission2 = new EmissionData(
                LocalDate.of(2020, 4, 2), "Industria", 250.0, 2, "MARN");

        entityManager.persist(emission1);
        entityManager.persist(emission2);
        entityManager.flush();

        // When
        LocalDate startDate = LocalDate.of(2020, 4, 1);
        LocalDate endDate = LocalDate.of(2020, 4, 30);
        List<EmissionData> result = emissionDataRepository.findBySectorAndMeasurementDateBetween(
                "Transporte", startDate, endDate);

        // Then
        assertEquals(1, result.size());
        assertEquals("Transporte", result.get(0).getSector());
        assertEquals(LocalDate.of(2020, 4, 1), result.get(0).getMeasurementDate());
    }

    @Test
    void testFindByYear() {
        // Given
        EmissionData emission1 = new EmissionData(
                LocalDate.of(2020, 4, 1), "Transporte", 150.5, 3, "MARN");
        EmissionData emission2 = new EmissionData(
                LocalDate.of(2021, 4, 1), "Transporte", 160.0, 2, "MARN");

        entityManager.persist(emission1);
        entityManager.persist(emission2);
        entityManager.flush();

        // When
        List<EmissionData> result2020 = emissionDataRepository.findByYear(2020);
        List<EmissionData> result2021 = emissionDataRepository.findByYear(2021);

        // Then
        assertEquals(1, result2020.size());
        assertEquals(2020, result2020.get(0).getMeasurementDate().getYear());

        assertEquals(1, result2021.size());
        assertEquals(2021, result2021.get(0).getMeasurementDate().getYear());
    }
}