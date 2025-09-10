package com.emissions.api.service;

import com.emissions.api.model.EmissionData;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface EmissionDataService {

    List<EmissionData> findAll();

    EmissionData findById(Long id);

    EmissionData save(EmissionData emissionData);

    EmissionData update(Long id, EmissionData emissionData);

    void delete(Long id);

    List<EmissionData> findBySector(String sector);

    List<EmissionData> findByDateRange(LocalDate startDate, LocalDate endDate);

    List<EmissionData> findBySectorAndDateRange(String sector, LocalDate startDate, LocalDate endDate);

    List<EmissionData> findByRestrictionLevel(Integer restrictionLevel);

    List<EmissionData> findByYear(int year);

    Map<String, Double> getAverageEmissionsBySector(LocalDate startDate, LocalDate endDate);

    Map<String, Double> getReductionPercentageBySector(LocalDate pandemicStart, LocalDate pandemicEnd,
                                                       LocalDate baselineStart, LocalDate baselineEnd);
}