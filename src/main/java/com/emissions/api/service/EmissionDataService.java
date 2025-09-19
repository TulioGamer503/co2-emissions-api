package com.emissions.api.service;

import com.emissions.api.model.EmissionData;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Servicio de dominio para gestionar datos de emisiones.
 * Define operaciones CRUD y consultas de agregación/analíticas por sector y fechas.
 */
public interface EmissionDataService {

    // ------------------------------- CRUD básico -------------------------------
    /** Operaciones estándar para listar, obtener por id, crear, actualizar y eliminar. */
    List<EmissionData> findAll();
    EmissionData findById(Long id);
    EmissionData save(EmissionData emissionData);
    EmissionData update(Long id, EmissionData emissionData);
    void delete(Long id);

    // ------------------------------- Consultas simples -------------------------------
    /** Búsquedas por sector, rango de fechas, combinación y filtros específicos. */
    List<EmissionData> findBySector(String sector);
    List<EmissionData> findByDateRange(LocalDate startDate, LocalDate endDate);
    List<EmissionData> findBySectorAndDateRange(String sector, LocalDate startDate, LocalDate endDate);
    List<EmissionData> findByRestrictionLevel(Integer restrictionLevel);
    List<EmissionData> findByYear(int year);

    // ------------------------------- Agregaciones/analítica -------------------------------
    /** Promedios y cálculo de porcentajes de reducción por sector en periodos definidos. */
    Map<String, Double> getAverageEmissionsBySector(LocalDate startDate, LocalDate endDate);
    Map<String, Double> getReductionPercentageBySector(LocalDate pandemicStart, LocalDate pandemicEnd,
                                                       LocalDate baselineStart, LocalDate baselineEnd);
}
