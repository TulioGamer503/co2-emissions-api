package com.emissions.api.service;

import com.emissions.api.model.RestrictionMeasure;
import java.time.LocalDate;
import java.util.List;

public interface RestrictionMeasureService {

    List<RestrictionMeasure> findAll();

    RestrictionMeasure findById(Long id);

    RestrictionMeasure save(RestrictionMeasure restrictionMeasure);

    RestrictionMeasure update(Long id, RestrictionMeasure restrictionMeasure);

    void delete(Long id);

    List<RestrictionMeasure> findByAffectedSector(String sector);

    List<RestrictionMeasure> findByIntensityLevel(Integer intensityLevel);

    List<RestrictionMeasure> findByDateRange(LocalDate startDate, LocalDate endDate);

    List<RestrictionMeasure> findBySectorAndDateRange(String sector, LocalDate startDate, LocalDate endDate);

    List<RestrictionMeasure> findByMinIntensityLevel(Integer minLevel);
}