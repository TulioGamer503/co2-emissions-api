package com.emissions.api.repository;

import com.emissions.api.model.RestrictionMeasure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repositorio JPA para medidas de restricción.
 * Incluye consultas derivadas y JPQL para filtros por sector, intensidad y fechas.
 */
@Repository
public interface RestrictionMeasureRepository extends JpaRepository<RestrictionMeasure, Long> {

    /** Buscar medidas por sector afectado exacto. */
    List<RestrictionMeasure> findByAffectedSector(String sector);

    /** Buscar medidas por nivel de intensidad exacto. */
    List<RestrictionMeasure> findByIntensityLevel(Integer intensityLevel);

    /** Buscar medidas cuyo startDate esté entre dos fechas (inclusive). */
    List<RestrictionMeasure> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

    /** Buscar por sector y rango de fechas mediante JPQL. */
    @Query("SELECT r FROM RestrictionMeasure r WHERE r.affectedSector = :sector AND r.startDate BETWEEN :startDate AND :endDate")
    List<RestrictionMeasure> findBySectorAndDateRange(@Param("sector") String sector,
                                                      @Param("startDate") LocalDate startDate,
                                                      @Param("endDate") LocalDate endDate);

    /** Buscar medidas con intensidad mínima (>= minLevel). */
    @Query("SELECT r FROM RestrictionMeasure r WHERE r.intensityLevel >= :minLevel")
    List<RestrictionMeasure> findByMinIntensityLevel(@Param("minLevel") Integer minLevel);
}
