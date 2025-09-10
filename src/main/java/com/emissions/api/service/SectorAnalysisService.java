package com.emissions.api.service;

import com.emissions.api.model.SectorAnalysis;
import java.time.LocalDate;
import java.util.List;

public interface SectorAnalysisService {

    List<SectorAnalysis> findAll();

    SectorAnalysis findById(Long id);

    SectorAnalysis save(SectorAnalysis sectorAnalysis);

    SectorAnalysis update(Long id, SectorAnalysis sectorAnalysis);

    void delete(Long id);

    List<SectorAnalysis> findBySector(String sector);

    List<SectorAnalysis> findByDateRange(LocalDate startDate, LocalDate endDate);

    List<SectorAnalysis> findBySectorAndDateRange(String sector, LocalDate startDate, LocalDate endDate);

    List<SectorAnalysis> findByMinReductionPercentage(Double minPercentage);

    List<SectorAnalysis> findByMinRestrictionLevel(Double minLevel);

    SectorAnalysis generateAnalysisForSector(String sector, LocalDate startDate, LocalDate endDate);
}