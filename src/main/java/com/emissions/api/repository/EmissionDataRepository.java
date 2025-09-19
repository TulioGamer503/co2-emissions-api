package com.emissions.api.repository;

import com.emissions.api.model.EmissionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repositorio JPA para acceder a datos de emisiones.
 * Provee consultas derivadas y JPQL personalizadas para filtros comunes.
 */
@Repository
public interface EmissionDataRepository extends JpaRepository<EmissionData, Long> {

    /** Buscar registros por sector exacto. */
    List<EmissionData> findBySector(String sector);

    /** Buscar registros cuyo measurementDate esté entre dos fechas (inclusive). */
    List<EmissionData> findByMeasurementDateBetween(LocalDate startDate, LocalDate endDate);

    /** Buscar por sector y rango de fechas. */
    List<EmissionData> findBySectorAndMeasurementDateBetween(String sector, LocalDate startDate, LocalDate endDate);

    /** Buscar por nivel de restricción exacto (JPQL con parámetro). */
    @Query("SELECT e FROM EmissionData e WHERE e.restrictionLevel = :level")
    List<EmissionData> findByRestrictionLevel(@Param("level") Integer restrictionLevel);

    /** Buscar registros por año calendario de measurementDate (función YEAR en JPQL). */
    @Query("SELECT e FROM EmissionData e WHERE YEAR(e.measurementDate) = :year")
    List<EmissionData> findByYear(@Param("year") int year);

    /** Promedio de emisiones por sector dentro de un rango de fechas (resultado: sector, promedio). */
    @Query("SELECT e.sector, AVG(e.co2Emissions) FROM EmissionData e " +
            "WHERE e.measurementDate BETWEEN :startDate AND :endDate " +
            "GROUP BY e.sector")
    List<Object[]> findAverageEmissionsBySector(@Param("startDate") LocalDate startDate,
                                                @Param("endDate") LocalDate endDate);
}
