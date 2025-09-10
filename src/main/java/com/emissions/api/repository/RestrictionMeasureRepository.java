package com.emissions.api.repository;

import com.emissions.api.model.RestrictionMeasure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RestrictionMeasureRepository extends JpaRepository<RestrictionMeasure, Long> {

    List<RestrictionMeasure> findByAffectedSector(String sector);

    List<RestrictionMeasure> findByIntensityLevel(Integer intensityLevel);

    List<RestrictionMeasure> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT r FROM RestrictionMeasure r WHERE r.affectedSector = :sector AND r.startDate BETWEEN :startDate AND :endDate")
    List<RestrictionMeasure> findBySectorAndDateRange(@Param("sector") String sector,
                                                      @Param("startDate") LocalDate startDate,
                                                      @Param("endDate") LocalDate endDate);

    @Query("SELECT r FROM RestrictionMeasure r WHERE r.intensityLevel >= :minLevel")
    List<RestrictionMeasure> findByMinIntensityLevel(@Param("minLevel") Integer minLevel);
}