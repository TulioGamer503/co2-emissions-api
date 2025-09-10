package com.emissions.api.repository;

import com.emissions.api.model.SectorAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SectorAnalysisRepository extends JpaRepository<SectorAnalysis, Long> {

    List<SectorAnalysis> findBySector(String sector);

    List<SectorAnalysis> findByAnalysisDateBetween(LocalDate startDate, LocalDate endDate);

    List<SectorAnalysis> findBySectorAndAnalysisDateBetween(String sector, LocalDate startDate, LocalDate endDate);

    @Query("SELECT s FROM SectorAnalysis s WHERE s.reductionPercentage > :minPercentage")
    List<SectorAnalysis> findByMinReductionPercentage(@Param("minPercentage") Double minPercentage);

    @Query("SELECT s FROM SectorAnalysis s WHERE s.averageRestrictionLevel >= :minLevel")
    List<SectorAnalysis> findByMinRestrictionLevel(@Param("minLevel") Double minLevel);
}