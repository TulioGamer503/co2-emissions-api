package com.emissions.api.service;

import com.emissions.api.model.SectorAnalysis;
import java.time.LocalDate;
import java.util.List;

/**
 * Servicio de dominio para gestionar análisis sectoriales.
 * Declara operaciones CRUD, consultas por filtros y generación automática de análisis.
 */
public interface SectorAnalysisService {

    // ------------------------------- CRUD básico -------------------------------
    /** Operaciones estándar para listar, obtener por id, crear, actualizar y eliminar. */
    List<SectorAnalysis> findAll();
    SectorAnalysis findById(Long id);
    SectorAnalysis save(SectorAnalysis sectorAnalysis);
    SectorAnalysis update(Long id, SectorAnalysis sectorAnalysis);
    void delete(Long id);

    // ------------------------------- Consultas por filtros -------------------------------
    /** Búsquedas por sector, por rango de fechas y su combinación. */
    List<SectorAnalysis> findBySector(String sector);
    List<SectorAnalysis> findByDateRange(LocalDate startDate, LocalDate endDate);
    List<SectorAnalysis> findBySectorAndDateRange(String sector, LocalDate startDate, LocalDate endDate);

    /** Filtros mínimos por porcentaje de reducción y nivel promedio de restricción. */
    List<SectorAnalysis> findByMinReductionPercentage(Double minPercentage);
    List<SectorAnalysis> findByMinRestrictionLevel(Double minLevel);

    // ------------------------------- Generación analítica -------------------------------
    /** Genera un análisis sectorial automáticamente para un sector y periodo. */
    SectorAnalysis generateAnalysisForSector(String sector, LocalDate startDate, LocalDate endDate);
}
