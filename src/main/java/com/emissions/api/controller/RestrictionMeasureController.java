package com.emissions.api.controller;

import com.emissions.api.model.RestrictionMeasure;
import com.emissions.api.service.RestrictionMeasureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Controlador REST para gestionar medidas de restricción durante la pandemia.
 * Expone endpoints CRUD y consultas por sector, intensidad y rangos de fecha.
 */
@RestController
@RequestMapping("/api/restriction-measures")
@Tag(name = "Medidas de Restricción", description = "API para gestionar medidas de restricción implementadas durante la pandemia")
public class RestrictionMeasureController {

    private final RestrictionMeasureService restrictionMeasureService;

    /**
     * Constructor con inyección del servicio de medidas de restricción.
     */
    @Autowired
    public RestrictionMeasureController(RestrictionMeasureService restrictionMeasureService) {
        this.restrictionMeasureService = restrictionMeasureService;
    }

    /**
     * Obtener todas las medidas de restricción.
     */
    @GetMapping
    @Operation(summary = "Obtener todas las medidas de restricción")
    public ResponseEntity<List<RestrictionMeasure>> getAllMeasures() {
        List<RestrictionMeasure> measures = restrictionMeasureService.findAll();
        return ResponseEntity.ok(measures);
    }

    /**
     * Obtener una medida de restricción por ID.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener medida de restricción por ID")
    public ResponseEntity<RestrictionMeasure> getMeasureById(@PathVariable Long id) {
        RestrictionMeasure measure = restrictionMeasureService.findById(id);
        return ResponseEntity.ok(measure);
    }

    /**
     * Crear una nueva medida de restricción.
     */
    @PostMapping
    @Operation(summary = "Crear nueva medida de restricción")
    public ResponseEntity<RestrictionMeasure> createMeasure(@Valid @RequestBody RestrictionMeasure measure) {
        RestrictionMeasure savedMeasure = restrictionMeasureService.save(measure);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMeasure);
    }

    /**
     * Actualizar una medida de restricción existente por ID.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar medida de restricción existente")
    public ResponseEntity<RestrictionMeasure> updateMeasure(@PathVariable Long id,
                                                            @Valid @RequestBody RestrictionMeasure measure) {
        RestrictionMeasure updatedMeasure = restrictionMeasureService.update(id, measure);
        return ResponseEntity.ok(updatedMeasure);
    }

    /**
     * Eliminar una medida de restricción por ID.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar medida de restricción")
    public ResponseEntity<Void> deleteMeasure(@PathVariable Long id) {
        restrictionMeasureService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Obtener medidas de restricción filtradas por sector afectado.
     */
    @GetMapping("/sector/{sector}")
    @Operation(summary = "Obtener medidas de restricción por sector afectado")
    public ResponseEntity<List<RestrictionMeasure>> getMeasuresBySector(@PathVariable String sector) {
        List<RestrictionMeasure> measures = restrictionMeasureService.findByAffectedSector(sector);
        return ResponseEntity.ok(measures);
    }

    /**
     * Obtener medidas de restricción filtradas por nivel de intensidad.
     */
    @GetMapping("/intensity/{level}")
    @Operation(summary = "Obtener medidas de restricción por nivel de intensidad")
    public ResponseEntity<List<RestrictionMeasure>> getMeasuresByIntensity(@PathVariable Integer level) {
        List<RestrictionMeasure> measures = restrictionMeasureService.findByIntensityLevel(level);
        return ResponseEntity.ok(measures);
    }

    /**
     * Obtener medidas de restricción dentro de un rango de fechas.
     */
    @GetMapping("/date-range")
    @Operation(summary = "Obtener medidas de restricción por rango de fechas")
    public ResponseEntity<List<RestrictionMeasure>> getMeasuresByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<RestrictionMeasure> measures = restrictionMeasureService.findByDateRange(startDate, endDate);
        return ResponseEntity.ok(measures);
    }

    /**
     * Obtener medidas de restricción filtradas por sector y rango de fechas.
     */
    @GetMapping("/sector-date-range")
    @Operation(summary = "Obtener medidas de restricción por sector y rango de fechas")
    public ResponseEntity<List<RestrictionMeasure>> getMeasuresBySectorAndDateRange(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<RestrictionMeasure> measures = restrictionMeasureService.findBySectorAndDateRange(sector, startDate, endDate);
        return ResponseEntity.ok(measures);
    }

    /**
     * Obtener medidas de restricción con un nivel mínimo de intensidad.
     */
    @GetMapping("/min-intensity/{minLevel}")
    @Operation(summary = "Obtener medidas de restricción con intensidad mínima")
    public ResponseEntity<List<RestrictionMeasure>> getMeasuresByMinIntensity(@PathVariable Integer minLevel) {
        List<RestrictionMeasure> measures = restrictionMeasureService.findByMinIntensityLevel(minLevel);
        return ResponseEntity.ok(measures);
    }
}
