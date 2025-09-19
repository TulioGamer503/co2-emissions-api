package com.emissions.api.service;

import com.emissions.api.model.RestrictionMeasure;
import java.time.LocalDate;
import java.util.List;

/**
 * Servicio de dominio para gestionar medidas de restricción.
 * Define operaciones CRUD y consultas específicas por sector, intensidad y fechas.
 */
public interface RestrictionMeasureService {

    // ------------------------------- CRUD básico -------------------------------
    /** Operaciones estándar para listar, obtener por id, crear, actualizar y eliminar. */
    List<RestrictionMeasure> findAll();
    RestrictionMeasure findById(Long id);
    RestrictionMeasure save(RestrictionMeasure restrictionMeasure);
    RestrictionMeasure update(Long id, RestrictionMeasure restrictionMeasure);
    void delete(Long id);

    // ------------------------------- Consultas simples -------------------------------
    /** Filtros directos por sector afectado y nivel de intensidad. */
    List<RestrictionMeasure> findByAffectedSector(String sector);
    List<RestrictionMeasure> findByIntensityLevel(Integer intensityLevel);

    /** Búsquedas por rango de fechas y combinación con sector. */
    List<RestrictionMeasure> findByDateRange(LocalDate startDate, LocalDate endDate);
    List<RestrictionMeasure> findBySectorAndDateRange(String sector, LocalDate startDate, LocalDate endDate);

    /** Medidas cuya intensidad sea mayor o igual a un umbral. */
    List<RestrictionMeasure> findByMinIntensityLevel(Integer minLevel);
}
