package com.emissions.api.controller;

import com.emissions.api.model.EmissionData;
import com.emissions.api.service.EmissionDataService;
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
import java.util.Map;

/**
 * Controlador REST para gestionar los datos de emisiones de CO₂.
 *
 * Expone endpoints CRUD y consultas personalizadas como búsquedas
 * por sector, fechas, niveles de restricción, promedios y reducciones.
 */
@RestController
@RequestMapping("/api/emissions")
@Tag(name = "Datos de Emisiones", description = "API para gestionar datos de emisiones de CO₂")
public class EmissionDataController {

    private final EmissionDataService emissionDataService;

    /**
     * Constructor que inyecta el servicio de emisiones.
     */
    @Autowired
    public EmissionDataController(EmissionDataService emissionDataService) {
        this.emissionDataService = emissionDataService;
    }

    /**
     * Obtener todos los registros de emisiones.
     */
    @GetMapping
    @Operation(summary = "Obtener todos los datos de emisiones")
    public ResponseEntity<List<EmissionData>> getAllEmissions() {
        List<EmissionData> emissions = emissionDataService.findAll();
        return ResponseEntity.ok(emissions);
    }

    /**
     * Obtener un registro de emisiones por ID.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener datos de emisión por ID")
    public ResponseEntity<EmissionData> getEmissionById(@PathVariable Long id) {
        EmissionData emissionData = emissionDataService.findById(id);
        return ResponseEntity.ok(emissionData);
    }

    /**
     * Crear un nuevo registro de emisiones.
     */
    @PostMapping
    @Operation(summary = "Crear nuevos datos de emisión")
    public ResponseEntity<EmissionData> createEmission(@Valid @RequestBody EmissionData emissionData) {
        EmissionData savedEmission = emissionDataService.save(emissionData);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmission);
    }

    /**
     * Actualizar un registro de emisiones existente por ID.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar datos de emisión existentes")
    public ResponseEntity<EmissionData> updateEmission(@PathVariable Long id,
                                                       @Valid @RequestBody EmissionData emissionData) {
        EmissionData updatedEmission = emissionDataService.update(id, emissionData);
        return ResponseEntity.ok(updatedEmission);
    }

    /**
     * Eliminar un registro de emisiones por ID.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar datos de emisión")
    public ResponseEntity<Void> deleteEmission(@PathVariable Long id) {
        emissionDataService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Obtener emisiones filtradas por sector.
     */
    @GetMapping("/sector/{sector}")
    @Operation(summary = "Obtener datos de emisión por sector")
    public ResponseEntity<List<EmissionData>> getEmissionsBySector(@PathVariable String sector) {
        List<EmissionData> emissions = emissionDataService.findBySector(sector);
        return ResponseEntity.ok(emissions);
    }

    /**
     * Obtener emisiones en un rango de fechas.
     */
    @GetMapping("/date-range")
    @Operation(summary = "Obtener datos de emisión por rango de fechas")
    public ResponseEntity<List<EmissionData>> getEmissionsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<EmissionData> emissions = emissionDataService.findByDateRange(startDate, endDate);
        return ResponseEntity.ok(emissions);
    }

    /**
     * Obtener emisiones filtradas por sector y rango de fechas.
     */
    @GetMapping("/sector-date-range")
    @Operation(summary = "Obtener datos de emisión por sector y rango de fechas")
    public ResponseEntity<List<EmissionData>> getEmissionsBySectorAndDateRange(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<EmissionData> emissions = emissionDataService.findBySectorAndDateRange(sector, startDate, endDate);
        return ResponseEntity.ok(emissions);
    }

    /**
     * Obtener emisiones filtradas por nivel de restricción.
     */
    @GetMapping("/restriction-level/{level}")
    @Operation(summary = "Obtener datos de emisión por nivel de restricción")
    public ResponseEntity<List<EmissionData>> getEmissionsByRestrictionLevel(@PathVariable Integer level) {
        List<EmissionData> emissions = emissionDataService.findByRestrictionLevel(level);
        return ResponseEntity.ok(emissions);
    }

    /**
     * Obtener emisiones de un año específico.
     */
    @GetMapping("/year/{year}")
    @Operation(summary = "Obtener datos de emisión por año")
    public ResponseEntity<List<EmissionData>> getEmissionsByYear(@PathVariable int year) {
        List<EmissionData> emissions = emissionDataService.findByYear(year);
        return ResponseEntity.ok(emissions);
    }

    /**
     * Obtener promedios de emisiones por sector en un rango de fechas.
     */
    @GetMapping("/average-by-sector")
    @Operation(summary = "Obtener promedios de emisiones por sector en un rango de fechas")
    public ResponseEntity<Map<String, Double>> getAverageEmissionsBySector(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        Map<String, Double> averages = emissionDataService.getAverageEmissionsBySector(startDate, endDate);
        return ResponseEntity.ok(averages);
    }

    /**
     * Calcular porcentaje de reducción de emisiones por sector
     * comparando periodos de pandemia y línea base.
     */
    @GetMapping("/reduction-percentage")
    @Operation(summary = "Calcular porcentaje de reducción de emisiones por sector")
    public ResponseEntity<Map<String, Double>> getReductionPercentage(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate pandemicStart,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate pandemicEnd,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate baselineStart,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate baselineEnd) {
        Map<String, Double> reductions = emissionDataService.getReductionPercentageBySector(
                pandemicStart, pandemicEnd, baselineStart, baselineEnd);
        return ResponseEntity.ok(reductions);
    }
}
