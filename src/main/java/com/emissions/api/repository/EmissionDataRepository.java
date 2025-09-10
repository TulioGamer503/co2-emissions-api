package com.emissions.api.repository;

import com.emissions.api.model.EmissionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmissionDataRepository extends JpaRepository<EmissionData, Long> {

    List<EmissionData> findBySector(String sector);

    List<EmissionData> findByMeasurementDateBetween(LocalDate startDate, LocalDate endDate);

    List<EmissionData> findBySectorAndMeasurementDateBetween(String sector, LocalDate startDate, LocalDate endDate);

    @Query("SELECT e FROM EmissionData e WHERE e.restrictionLevel = :level")
    List<EmissionData> findByRestrictionLevel(@Param("level") Integer restrictionLevel);

    @Query("SELECT e FROM EmissionData e WHERE YEAR(e.measurementDate) = :year")
    List<EmissionData> findByYear(@Param("year") int year);

    @Query("SELECT e.sector, AVG(e.co2Emissions) FROM EmissionData e " +
            "WHERE e.measurementDate BETWEEN :startDate AND :endDate " +
            "GROUP BY e.sector")
    List<Object[]> findAverageEmissionsBySector(@Param("startDate") LocalDate startDate,
                                                @Param("endDate") LocalDate endDate);
}