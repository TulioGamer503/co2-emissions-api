package com.emissions.api.repository;

import com.emissions.api.model.SectorAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repositorio JPA para análisis sectoriales.
 * Ofrece consultas derivadas y JPQL para filtros por sector, fechas, reducción y restricción.
 */
@Repository
public interface SectorAnalysisRepository extends JpaRepository<SectorAnalysis, Long> {

    /** Buscar análisis por nombre de sector exacto. */
    List<SectorAnalysis> findBySector(String sector);

    /** Buscar análisis cuyo analysisDate esté entre dos fechas (inclusive). */
    List<SectorAnalysis> findByAnalysisDateBetween(LocalDate startDate, LocalDate endDate);

    /** Buscar por sector y rango de fechas. */
    List<SectorAnalysis> findBySectorAndAnalysisDateBetween(String sector, LocalDate startDate, LocalDate endDate);

    /** Buscar análisis con porcentaje de reducción superior a un mínimo (JPQL). */
    @Query("SELECT s FROM SectorAnalysis s WHERE s.reductionPercentage > :minPercentage")
    List<SectorAnalysis> findByMinReductionPercentage(@Param("minPercentage") Double minPercentage);

    /** Buscar análisis con nivel promedio de restricción mayor o igual a un mínimo (JPQL). */
    @Query("SELECT s FROM SectorAnalysis s WHERE s.averageRestrictionLevel >= :minLevel")
    List<SectorAnalysis> findByMinRestrictionLevel(@Param("minLevel") Double minLevel);
}
