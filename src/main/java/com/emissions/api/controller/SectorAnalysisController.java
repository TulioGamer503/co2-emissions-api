package com.emissions.api.controller;

import com.emissions.api.model.SectorAnalysis;
import com.emissions.api.service.SectorAnalysisService;
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

@RestController
@RequestMapping("/api/sector-analysis")
@Tag(name = "Análisis Sectorial", description = "API para gestionar análisis sectoriales de emisiones")
public class SectorAnalysisController {

    private final SectorAnalysisService sectorAnalysisService;

    @Autowired
    public SectorAnalysisController(SectorAnalysisService sectorAnalysisService) {
        this.sectorAnalysisService = sectorAnalysisService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los análisis sectoriales")
    public ResponseEntity<List<SectorAnalysis>> getAllAnalyses() {
        List<SectorAnalysis> analyses = sectorAnalysisService.findAll();
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener análisis sectorial por ID")
    public ResponseEntity<SectorAnalysis> getAnalysisById(@PathVariable Long id) {
        SectorAnalysis analysis = sectorAnalysisService.findById(id);
        return ResponseEntity.ok(analysis);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo análisis sectorial")
    public ResponseEntity<SectorAnalysis> createAnalysis(@Valid @RequestBody SectorAnalysis analysis) {
        SectorAnalysis savedAnalysis = sectorAnalysisService.save(analysis);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnalysis);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar análisis sectorial existente")
    public ResponseEntity<SectorAnalysis> updateAnalysis(@PathVariable Long id,
                                                         @Valid @RequestBody SectorAnalysis analysis) {
        SectorAnalysis updatedAnalysis = sectorAnalysisService.update(id, analysis);
        return ResponseEntity.ok(updatedAnalysis);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar análisis sectorial")
    public ResponseEntity<Void> deleteAnalysis(@PathVariable Long id) {
        sectorAnalysisService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sector/{sector}")
    @Operation(summary = "Obtener análisis sectoriales por sector")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesBySector(@PathVariable String sector) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findBySector(sector);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/date-range")
    @Operation(summary = "Obtener análisis sectoriales por rango de fechas")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByDateRange(startDate, endDate);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/sector-date-range")
    @Operation(summary = "Obtener análisis sectoriales por sector y rango de fechas")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesBySectorAndDateRange(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findBySectorAndDateRange(sector, startDate, endDate);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/min-reduction/{minPercentage}")
    @Operation(summary = "Obtener análisis sectoriales con porcentaje mínimo de reducción")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByMinReduction(@PathVariable Double minPercentage) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByMinReductionPercentage(minPercentage);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/min-restriction/{minLevel}")
    @Operation(summary = "Obtener análisis sectoriales con nivel mínimo de restricción")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByMinRestriction(@PathVariable Double minLevel) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByMinRestrictionLevel(minLevel);
        return ResponseEntity.ok(analyses);
    }

    @PostMapping("/generate")
    @Operation(summary = "Generar análisis automático para un sector y período")
    public ResponseEntity<SectorAnalysis> generateAnalysis(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        SectorAnalysis analysis = sectorAnalysisService.generateAnalysisForSector(sector, startDate, endDate);
        return ResponseEntity.status(HttpStatus.CREATED).body(analysis);
    }
}


package com.emissions.api.controller;

import com.emissions.api.model.SectorAnalysis;
import com.emissions.api.service.SectorAnalysisService;
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

@RestController
@RequestMapping("/api/sector-analysis")
@Tag(name = "Análisis Sectorial", description = "API para gestionar análisis sectoriales de emisiones")
public class SectorAnalysisController {

    private final SectorAnalysisService sectorAnalysisService;

    @Autowired
    public SectorAnalysisController(SectorAnalysisService sectorAnalysisService) {
        this.sectorAnalysisService = sectorAnalysisService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los análisis sectoriales")
    public ResponseEntity<List<SectorAnalysis>> getAllAnalyses() {
        List<SectorAnalysis> analyses = sectorAnalysisService.findAll();
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener análisis sectorial por ID")
    public ResponseEntity<SectorAnalysis> getAnalysisById(@PathVariable Long id) {
        SectorAnalysis analysis = sectorAnalysisService.findById(id);
        return ResponseEntity.ok(analysis);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo análisis sectorial")
    public ResponseEntity<SectorAnalysis> createAnalysis(@Valid @RequestBody SectorAnalysis analysis) {
        SectorAnalysis savedAnalysis = sectorAnalysisService.save(analysis);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnalysis);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar análisis sectorial existente")
    public ResponseEntity<SectorAnalysis> updateAnalysis(@PathVariable Long id,
                                                         @Valid @RequestBody SectorAnalysis analysis) {
        SectorAnalysis updatedAnalysis = sectorAnalysisService.update(id, analysis);
        return ResponseEntity.ok(updatedAnalysis);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar análisis sectorial")
    public ResponseEntity<Void> deleteAnalysis(@PathVariable Long id) {
        sectorAnalysisService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sector/{sector}")
    @Operation(summary = "Obtener análisis sectoriales por sector")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesBySector(@PathVariable String sector) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findBySector(sector);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/date-range")
    @Operation(summary = "Obtener análisis sectoriales por rango de fechas")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByDateRange(startDate, endDate);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/sector-date-range")
    @Operation(summary = "Obtener análisis sectoriales por sector y rango de fechas")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesBySectorAndDateRange(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findBySectorAndDateRange(sector, startDate, endDate);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/min-reduction/{minPercentage}")
    @Operation(summary = "Obtener análisis sectoriales con porcentaje mínimo de reducción")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByMinReduction(@PathVariable Double minPercentage) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByMinReductionPercentage(minPercentage);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/min-restriction/{minLevel}")
    @Operation(summary = "Obtener análisis sectoriales con nivel mínimo de restricción")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByMinRestriction(@PathVariable Double minLevel) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByMinRestrictionLevel(minLevel);
        return ResponseEntity.ok(analyses);
    }

    @PostMapping("/generate")
    @Operation(summary = "Generar análisis automático para un sector y período")
    public ResponseEntity<SectorAnalysis> generateAnalysis(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        SectorAnalysis analysis = sectorAnalysisService.generateAnalysisForSector(sector, startDate, endDate);
        return ResponseEntity.status(HttpStatus.CREATED).body(analysis);
    }
}


package com.emissions.api.controller;

import com.emissions.api.model.SectorAnalysis;
import com.emissions.api.service.SectorAnalysisService;
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

@RestController
@RequestMapping("/api/sector-analysis")
@Tag(name = "Análisis Sectorial", description = "API para gestionar análisis sectoriales de emisiones")
public class SectorAnalysisController {

    private final SectorAnalysisService sectorAnalysisService;

    @Autowired
    public SectorAnalysisController(SectorAnalysisService sectorAnalysisService) {
        this.sectorAnalysisService = sectorAnalysisService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los análisis sectoriales")
    public ResponseEntity<List<SectorAnalysis>> getAllAnalyses() {
        List<SectorAnalysis> analyses = sectorAnalysisService.findAll();
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener análisis sectorial por ID")
    public ResponseEntity<SectorAnalysis> getAnalysisById(@PathVariable Long id) {
        SectorAnalysis analysis = sectorAnalysisService.findById(id);
        return ResponseEntity.ok(analysis);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo análisis sectorial")
    public ResponseEntity<SectorAnalysis> createAnalysis(@Valid @RequestBody SectorAnalysis analysis) {
        SectorAnalysis savedAnalysis = sectorAnalysisService.save(analysis);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnalysis);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar análisis sectorial existente")
    public ResponseEntity<SectorAnalysis> updateAnalysis(@PathVariable Long id,
                                                         @Valid @RequestBody SectorAnalysis analysis) {
        SectorAnalysis updatedAnalysis = sectorAnalysisService.update(id, analysis);
        return ResponseEntity.ok(updatedAnalysis);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar análisis sectorial")
    public ResponseEntity<Void> deleteAnalysis(@PathVariable Long id) {
        sectorAnalysisService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sector/{sector}")
    @Operation(summary = "Obtener análisis sectoriales por sector")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesBySector(@PathVariable String sector) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findBySector(sector);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/date-range")
    @Operation(summary = "Obtener análisis sectoriales por rango de fechas")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByDateRange(startDate, endDate);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/sector-date-range")
    @Operation(summary = "Obtener análisis sectoriales por sector y rango de fechas")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesBySectorAndDateRange(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findBySectorAndDateRange(sector, startDate, endDate);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/min-reduction/{minPercentage}")
    @Operation(summary = "Obtener análisis sectoriales con porcentaje mínimo de reducción")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByMinReduction(@PathVariable Double minPercentage) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByMinReductionPercentage(minPercentage);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/min-restriction/{minLevel}")
    @Operation(summary = "Obtener análisis sectoriales con nivel mínimo de restricción")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByMinRestriction(@PathVariable Double minLevel) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByMinRestrictionLevel(minLevel);
        return ResponseEntity.ok(analyses);
    }

    @PostMapping("/generate")
    @Operation(summary = "Generar análisis automático para un sector y período")
    public ResponseEntity<SectorAnalysis> generateAnalysis(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        SectorAnalysis analysis = sectorAnalysisService.generateAnalysisForSector(sector, startDate, endDate);
        return ResponseEntity.status(HttpStatus.CREATED).body(analysis);
    }
}


package com.emissions.api.controller;

import com.emissions.api.model.SectorAnalysis;
import com.emissions.api.service.SectorAnalysisService;
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

@RestController
@RequestMapping("/api/sector-analysis")
@Tag(name = "Análisis Sectorial", description = "API para gestionar análisis sectoriales de emisiones")
public class SectorAnalysisController {

    private final SectorAnalysisService sectorAnalysisService;

    @Autowired
    public SectorAnalysisController(SectorAnalysisService sectorAnalysisService) {
        this.sectorAnalysisService = sectorAnalysisService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los análisis sectoriales")
    public ResponseEntity<List<SectorAnalysis>> getAllAnalyses() {
        List<SectorAnalysis> analyses = sectorAnalysisService.findAll();
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener análisis sectorial por ID")
    public ResponseEntity<SectorAnalysis> getAnalysisById(@PathVariable Long id) {
        SectorAnalysis analysis = sectorAnalysisService.findById(id);
        return ResponseEntity.ok(analysis);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo análisis sectorial")
    public ResponseEntity<SectorAnalysis> createAnalysis(@Valid @RequestBody SectorAnalysis analysis) {
        SectorAnalysis savedAnalysis = sectorAnalysisService.save(analysis);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnalysis);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar análisis sectorial existente")
    public ResponseEntity<SectorAnalysis> updateAnalysis(@PathVariable Long id,
                                                         @Valid @RequestBody SectorAnalysis analysis) {
        SectorAnalysis updatedAnalysis = sectorAnalysisService.update(id, analysis);
        return ResponseEntity.ok(updatedAnalysis);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar análisis sectorial")
    public ResponseEntity<Void> deleteAnalysis(@PathVariable Long id) {
        sectorAnalysisService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sector/{sector}")
    @Operation(summary = "Obtener análisis sectoriales por sector")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesBySector(@PathVariable String sector) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findBySector(sector);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/date-range")
    @Operation(summary = "Obtener análisis sectoriales por rango de fechas")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByDateRange(startDate, endDate);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/sector-date-range")
    @Operation(summary = "Obtener análisis sectoriales por sector y rango de fechas")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesBySectorAndDateRange(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findBySectorAndDateRange(sector, startDate, endDate);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/min-reduction/{minPercentage}")
    @Operation(summary = "Obtener análisis sectoriales con porcentaje mínimo de reducción")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByMinReduction(@PathVariable Double minPercentage) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByMinReductionPercentage(minPercentage);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/min-restriction/{minLevel}")
    @Operation(summary = "Obtener análisis sectoriales con nivel mínimo de restricción")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByMinRestriction(@PathVariable Double minLevel) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByMinRestrictionLevel(minLevel);
        return ResponseEntity.ok(analyses);
    }

    @PostMapping("/generate")
    @Operation(summary = "Generar análisis automático para un sector y período")
    public ResponseEntity<SectorAnalysis> generateAnalysis(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        SectorAnalysis analysis = sectorAnalysisService.generateAnalysisForSector(sector, startDate, endDate);
        return ResponseEntity.status(HttpStatus.CREATED).body(analysis);
    }
}


package com.emissions.api.controller;

import com.emissions.api.model.SectorAnalysis;
import com.emissions.api.service.SectorAnalysisService;
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

@RestController
@RequestMapping("/api/sector-analysis")
@Tag(name = "Análisis Sectorial", description = "API para gestionar análisis sectoriales de emisiones")
public class SectorAnalysisController {

    private final SectorAnalysisService sectorAnalysisService;

    @Autowired
    public SectorAnalysisController(SectorAnalysisService sectorAnalysisService) {
        this.sectorAnalysisService = sectorAnalysisService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los análisis sectoriales")
    public ResponseEntity<List<SectorAnalysis>> getAllAnalyses() {
        List<SectorAnalysis> analyses = sectorAnalysisService.findAll();
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener análisis sectorial por ID")
    public ResponseEntity<SectorAnalysis> getAnalysisById(@PathVariable Long id) {
        SectorAnalysis analysis = sectorAnalysisService.findById(id);
        return ResponseEntity.ok(analysis);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo análisis sectorial")
    public ResponseEntity<SectorAnalysis> createAnalysis(@Valid @RequestBody SectorAnalysis analysis) {
        SectorAnalysis savedAnalysis = sectorAnalysisService.save(analysis);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnalysis);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar análisis sectorial existente")
    public ResponseEntity<SectorAnalysis> updateAnalysis(@PathVariable Long id,
                                                         @Valid @RequestBody SectorAnalysis analysis) {
        SectorAnalysis updatedAnalysis = sectorAnalysisService.update(id, analysis);
        return ResponseEntity.ok(updatedAnalysis);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar análisis sectorial")
    public ResponseEntity<Void> deleteAnalysis(@PathVariable Long id) {
        sectorAnalysisService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sector/{sector}")
    @Operation(summary = "Obtener análisis sectoriales por sector")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesBySector(@PathVariable String sector) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findBySector(sector);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/date-range")
    @Operation(summary = "Obtener análisis sectoriales por rango de fechas")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByDateRange(startDate, endDate);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/sector-date-range")
    @Operation(summary = "Obtener análisis sectoriales por sector y rango de fechas")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesBySectorAndDateRange(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findBySectorAndDateRange(sector, startDate, endDate);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/min-reduction/{minPercentage}")
    @Operation(summary = "Obtener análisis sectoriales con porcentaje mínimo de reducción")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByMinReduction(@PathVariable Double minPercentage) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByMinReductionPercentage(minPercentage);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/min-restriction/{minLevel}")
    @Operation(summary = "Obtener análisis sectoriales con nivel mínimo de restricción")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByMinRestriction(@PathVariable Double minLevel) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByMinRestrictionLevel(minLevel);
        return ResponseEntity.ok(analyses);
    }

    @PostMapping("/generate")
    @Operation(summary = "Generar análisis automático para un sector y período")
    public ResponseEntity<SectorAnalysis> generateAnalysis(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        SectorAnalysis analysis = sectorAnalysisService.generateAnalysisForSector(sector, startDate, endDate);
        return ResponseEntity.status(HttpStatus.CREATED).body(analysis);
    }
}


package com.emissions.api.controller;

import com.emissions.api.model.SectorAnalysis;
import com.emissions.api.service.SectorAnalysisService;
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

@RestController
@RequestMapping("/api/sector-analysis")
@Tag(name = "Análisis Sectorial", description = "API para gestionar análisis sectoriales de emisiones")
public class SectorAnalysisController {

    private final SectorAnalysisService sectorAnalysisService;

    @Autowired
    public SectorAnalysisController(SectorAnalysisService sectorAnalysisService) {
        this.sectorAnalysisService = sectorAnalysisService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los análisis sectoriales")
    public ResponseEntity<List<SectorAnalysis>> getAllAnalyses() {
        List<SectorAnalysis> analyses = sectorAnalysisService.findAll();
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener análisis sectorial por ID")
    public ResponseEntity<SectorAnalysis> getAnalysisById(@PathVariable Long id) {
        SectorAnalysis analysis = sectorAnalysisService.findById(id);
        return ResponseEntity.ok(analysis);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo análisis sectorial")
    public ResponseEntity<SectorAnalysis> createAnalysis(@Valid @RequestBody SectorAnalysis analysis) {
        SectorAnalysis savedAnalysis = sectorAnalysisService.save(analysis);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnalysis);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar análisis sectorial existente")
    public ResponseEntity<SectorAnalysis> updateAnalysis(@PathVariable Long id,
                                                         @Valid @RequestBody SectorAnalysis analysis) {
        SectorAnalysis updatedAnalysis = sectorAnalysisService.update(id, analysis);
        return ResponseEntity.ok(updatedAnalysis);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar análisis sectorial")
    public ResponseEntity<Void> deleteAnalysis(@PathVariable Long id) {
        sectorAnalysisService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sector/{sector}")
    @Operation(summary = "Obtener análisis sectoriales por sector")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesBySector(@PathVariable String sector) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findBySector(sector);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/date-range")
    @Operation(summary = "Obtener análisis sectoriales por rango de fechas")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByDateRange(startDate, endDate);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/sector-date-range")
    @Operation(summary = "Obtener análisis sectoriales por sector y rango de fechas")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesBySectorAndDateRange(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findBySectorAndDateRange(sector, startDate, endDate);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/min-reduction/{minPercentage}")
    @Operation(summary = "Obtener análisis sectoriales con porcentaje mínimo de reducción")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByMinReduction(@PathVariable Double minPercentage) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByMinReductionPercentage(minPercentage);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/min-restriction/{minLevel}")
    @Operation(summary = "Obtener análisis sectoriales con nivel mínimo de restricción")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByMinRestriction(@PathVariable Double minLevel) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByMinRestrictionLevel(minLevel);
        return ResponseEntity.ok(analyses);
    }

    @PostMapping("/generate")
    @Operation(summary = "Generar análisis automático para un sector y período")
    public ResponseEntity<SectorAnalysis> generateAnalysis(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        SectorAnalysis analysis = sectorAnalysisService.generateAnalysisForSector(sector, startDate, endDate);
        return ResponseEntity.status(HttpStatus.CREATED).body(analysis);
    }
}


package com.emissions.api.controller;

import com.emissions.api.model.SectorAnalysis;
import com.emissions.api.service.SectorAnalysisService;
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

@RestController
@RequestMapping("/api/sector-analysis")
@Tag(name = "Análisis Sectorial", description = "API para gestionar análisis sectoriales de emisiones")
public class SectorAnalysisController {

    private final SectorAnalysisService sectorAnalysisService;

    @Autowired
    public SectorAnalysisController(SectorAnalysisService sectorAnalysisService) {
        this.sectorAnalysisService = sectorAnalysisService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los análisis sectoriales")
    public ResponseEntity<List<SectorAnalysis>> getAllAnalyses() {
        List<SectorAnalysis> analyses = sectorAnalysisService.findAll();
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener análisis sectorial por ID")
    public ResponseEntity<SectorAnalysis> getAnalysisById(@PathVariable Long id) {
        SectorAnalysis analysis = sectorAnalysisService.findById(id);
        return ResponseEntity.ok(analysis);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo análisis sectorial")
    public ResponseEntity<SectorAnalysis> createAnalysis(@Valid @RequestBody SectorAnalysis analysis) {
        SectorAnalysis savedAnalysis = sectorAnalysisService.save(analysis);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnalysis);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar análisis sectorial existente")
    public ResponseEntity<SectorAnalysis> updateAnalysis(@PathVariable Long id,
                                                         @Valid @RequestBody SectorAnalysis analysis) {
        SectorAnalysis updatedAnalysis = sectorAnalysisService.update(id, analysis);
        return ResponseEntity.ok(updatedAnalysis);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar análisis sectorial")
    public ResponseEntity<Void> deleteAnalysis(@PathVariable Long id) {
        sectorAnalysisService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sector/{sector}")
    @Operation(summary = "Obtener análisis sectoriales por sector")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesBySector(@PathVariable String sector) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findBySector(sector);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/date-range")
    @Operation(summary = "Obtener análisis sectoriales por rango de fechas")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByDateRange(startDate, endDate);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/sector-date-range")
    @Operation(summary = "Obtener análisis sectoriales por sector y rango de fechas")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesBySectorAndDateRange(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findBySectorAndDateRange(sector, startDate, endDate);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/min-reduction/{minPercentage}")
    @Operation(summary = "Obtener análisis sectoriales con porcentaje mínimo de reducción")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByMinReduction(@PathVariable Double minPercentage) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByMinReductionPercentage(minPercentage);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/min-restriction/{minLevel}")
    @Operation(summary = "Obtener análisis sectoriales con nivel mínimo de restricción")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByMinRestriction(@PathVariable Double minLevel) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByMinRestrictionLevel(minLevel);
        return ResponseEntity.ok(analyses);
    }

    @PostMapping("/generate")
    @Operation(summary = "Generar análisis automático para un sector y período")
    public ResponseEntity<SectorAnalysis> generateAnalysis(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        SectorAnalysis analysis = sectorAnalysisService.generateAnalysisForSector(sector, startDate, endDate);
        return ResponseEntity.status(HttpStatus.CREATED).body(analysis);
    }
}


package com.emissions.api.controller;

import com.emissions.api.model.SectorAnalysis;
import com.emissions.api.service.SectorAnalysisService;
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

@RestController
@RequestMapping("/api/sector-analysis")
@Tag(name = "Análisis Sectorial", description = "API para gestionar análisis sectoriales de emisiones")
public class SectorAnalysisController {

    private final SectorAnalysisService sectorAnalysisService;

    @Autowired
    public SectorAnalysisController(SectorAnalysisService sectorAnalysisService) {
        this.sectorAnalysisService = sectorAnalysisService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los análisis sectoriales")
    public ResponseEntity<List<SectorAnalysis>> getAllAnalyses() {
        List<SectorAnalysis> analyses = sectorAnalysisService.findAll();
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener análisis sectorial por ID")
    public ResponseEntity<SectorAnalysis> getAnalysisById(@PathVariable Long id) {
        SectorAnalysis analysis = sectorAnalysisService.findById(id);
        return ResponseEntity.ok(analysis);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo análisis sectorial")
    public ResponseEntity<SectorAnalysis> createAnalysis(@Valid @RequestBody SectorAnalysis analysis) {
        SectorAnalysis savedAnalysis = sectorAnalysisService.save(analysis);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnalysis);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar análisis sectorial existente")
    public ResponseEntity<SectorAnalysis> updateAnalysis(@PathVariable Long id,
                                                         @Valid @RequestBody SectorAnalysis analysis) {
        SectorAnalysis updatedAnalysis = sectorAnalysisService.update(id, analysis);
        return ResponseEntity.ok(updatedAnalysis);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar análisis sectorial")
    public ResponseEntity<Void> deleteAnalysis(@PathVariable Long id) {
        sectorAnalysisService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sector/{sector}")
    @Operation(summary = "Obtener análisis sectoriales por sector")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesBySector(@PathVariable String sector) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findBySector(sector);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/date-range")
    @Operation(summary = "Obtener análisis sectoriales por rango de fechas")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByDateRange(startDate, endDate);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/sector-date-range")
    @Operation(summary = "Obtener análisis sectoriales por sector y rango de fechas")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesBySectorAndDateRange(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findBySectorAndDateRange(sector, startDate, endDate);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/min-reduction/{minPercentage}")
    @Operation(summary = "Obtener análisis sectoriales con porcentaje mínimo de reducción")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByMinReduction(@PathVariable Double minPercentage) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByMinReductionPercentage(minPercentage);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/min-restriction/{minLevel}")
    @Operation(summary = "Obtener análisis sectoriales con nivel mínimo de restricción")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByMinRestriction(@PathVariable Double minLevel) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByMinRestrictionLevel(minLevel);
        return ResponseEntity.ok(analyses);
    }

    @PostMapping("/generate")
    @Operation(summary = "Generar análisis automático para un sector y período")
    public ResponseEntity<SectorAnalysis> generateAnalysis(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        SectorAnalysis analysis = sectorAnalysisService.generateAnalysisForSector(sector, startDate, endDate);
        return ResponseEntity.status(HttpStatus.CREATED).body(analysis);
    }
}


package com.emissions.api.controller;

import com.emissions.api.model.SectorAnalysis;
import com.emissions.api.service.SectorAnalysisService;
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

@RestController
@RequestMapping("/api/sector-analysis")
@Tag(name = "Análisis Sectorial", description = "API para gestionar análisis sectoriales de emisiones")
public class SectorAnalysisController {

    private final SectorAnalysisService sectorAnalysisService;

    @Autowired
    public SectorAnalysisController(SectorAnalysisService sectorAnalysisService) {
        this.sectorAnalysisService = sectorAnalysisService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los análisis sectoriales")
    public ResponseEntity<List<SectorAnalysis>> getAllAnalyses() {
        List<SectorAnalysis> analyses = sectorAnalysisService.findAll();
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener análisis sectorial por ID")
    public ResponseEntity<SectorAnalysis> getAnalysisById(@PathVariable Long id) {
        SectorAnalysis analysis = sectorAnalysisService.findById(id);
        return ResponseEntity.ok(analysis);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo análisis sectorial")
    public ResponseEntity<SectorAnalysis> createAnalysis(@Valid @RequestBody SectorAnalysis analysis) {
        SectorAnalysis savedAnalysis = sectorAnalysisService.save(analysis);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnalysis);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar análisis sectorial existente")
    public ResponseEntity<SectorAnalysis> updateAnalysis(@PathVariable Long id,
                                                         @Valid @RequestBody SectorAnalysis analysis) {
        SectorAnalysis updatedAnalysis = sectorAnalysisService.update(id, analysis);
        return ResponseEntity.ok(updatedAnalysis);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar análisis sectorial")
    public ResponseEntity<Void> deleteAnalysis(@PathVariable Long id) {
        sectorAnalysisService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sector/{sector}")
    @Operation(summary = "Obtener análisis sectoriales por sector")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesBySector(@PathVariable String sector) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findBySector(sector);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/date-range")
    @Operation(summary = "Obtener análisis sectoriales por rango de fechas")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByDateRange(startDate, endDate);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/sector-date-range")
    @Operation(summary = "Obtener análisis sectoriales por sector y rango de fechas")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesBySectorAndDateRange(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findBySectorAndDateRange(sector, startDate, endDate);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/min-reduction/{minPercentage}")
    @Operation(summary = "Obtener análisis sectoriales con porcentaje mínimo de reducción")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByMinReduction(@PathVariable Double minPercentage) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByMinReductionPercentage(minPercentage);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/min-restriction/{minLevel}")
    @Operation(summary = "Obtener análisis sectoriales con nivel mínimo de restricción")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByMinRestriction(@PathVariable Double minLevel) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByMinRestrictionLevel(minLevel);
        return ResponseEntity.ok(analyses);
    }

    @PostMapping("/generate")
    @Operation(summary = "Generar análisis automático para un sector y período")
    public ResponseEntity<SectorAnalysis> generateAnalysis(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        SectorAnalysis analysis = sectorAnalysisService.generateAnalysisForSector(sector, startDate, endDate);
        return ResponseEntity.status(HttpStatus.CREATED).body(analysis);
    }
}


package com.emissions.api.controller;

import com.emissions.api.model.SectorAnalysis;
import com.emissions.api.service.SectorAnalysisService;
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

@RestController
@RequestMapping("/api/sector-analysis")
@Tag(name = "Análisis Sectorial", description = "API para gestionar análisis sectoriales de emisiones")
public class SectorAnalysisController {

    private final SectorAnalysisService sectorAnalysisService;

    @Autowired
    public SectorAnalysisController(SectorAnalysisService sectorAnalysisService) {
        this.sectorAnalysisService = sectorAnalysisService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los análisis sectoriales")
    public ResponseEntity<List<SectorAnalysis>> getAllAnalyses() {
        List<SectorAnalysis> analyses = sectorAnalysisService.findAll();
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener análisis sectorial por ID")
    public ResponseEntity<SectorAnalysis> getAnalysisById(@PathVariable Long id) {
        SectorAnalysis analysis = sectorAnalysisService.findById(id);
        return ResponseEntity.ok(analysis);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo análisis sectorial")
    public ResponseEntity<SectorAnalysis> createAnalysis(@Valid @RequestBody SectorAnalysis analysis) {
        SectorAnalysis savedAnalysis = sectorAnalysisService.save(analysis);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnalysis);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar análisis sectorial existente")
    public ResponseEntity<SectorAnalysis> updateAnalysis(@PathVariable Long id,
                                                         @Valid @RequestBody SectorAnalysis analysis) {
        SectorAnalysis updatedAnalysis = sectorAnalysisService.update(id, analysis);
        return ResponseEntity.ok(updatedAnalysis);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar análisis sectorial")
    public ResponseEntity<Void> deleteAnalysis(@PathVariable Long id) {
        sectorAnalysisService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sector/{sector}")
    @Operation(summary = "Obtener análisis sectoriales por sector")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesBySector(@PathVariable String sector) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findBySector(sector);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/date-range")
    @Operation(summary = "Obtener análisis sectoriales por rango de fechas")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByDateRange(startDate, endDate);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/sector-date-range")
    @Operation(summary = "Obtener análisis sectoriales por sector y rango de fechas")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesBySectorAndDateRange(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findBySectorAndDateRange(sector, startDate, endDate);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/min-reduction/{minPercentage}")
    @Operation(summary = "Obtener análisis sectoriales con porcentaje mínimo de reducción")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByMinReduction(@PathVariable Double minPercentage) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByMinReductionPercentage(minPercentage);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/min-restriction/{minLevel}")
    @Operation(summary = "Obtener análisis sectoriales con nivel mínimo de restricción")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByMinRestriction(@PathVariable Double minLevel) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByMinRestrictionLevel(minLevel);
        return ResponseEntity.ok(analyses);
    }

    @PostMapping("/generate")
    @Operation(summary = "Generar análisis automático para un sector y período")
    public ResponseEntity<SectorAnalysis> generateAnalysis(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        SectorAnalysis analysis = sectorAnalysisService.generateAnalysisForSector(sector, startDate, endDate);
        return ResponseEntity.status(HttpStatus.CREATED).body(analysis);
    }
}


package com.emissions.api.controller;

import com.emissions.api.model.SectorAnalysis;
import com.emissions.api.service.SectorAnalysisService;
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

@RestController
@RequestMapping("/api/sector-analysis")
@Tag(name = "Análisis Sectorial", description = "API para gestionar análisis sectoriales de emisiones")
public class SectorAnalysisController {

    private final SectorAnalysisService sectorAnalysisService;

    @Autowired
    public SectorAnalysisController(SectorAnalysisService sectorAnalysisService) {
        this.sectorAnalysisService = sectorAnalysisService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los análisis sectoriales")
    public ResponseEntity<List<SectorAnalysis>> getAllAnalyses() {
        List<SectorAnalysis> analyses = sectorAnalysisService.findAll();
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener análisis sectorial por ID")
    public ResponseEntity<SectorAnalysis> getAnalysisById(@PathVariable Long id) {
        SectorAnalysis analysis = sectorAnalysisService.findById(id);
        return ResponseEntity.ok(analysis);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo análisis sectorial")
    public ResponseEntity<SectorAnalysis> createAnalysis(@Valid @RequestBody SectorAnalysis analysis) {
        SectorAnalysis savedAnalysis = sectorAnalysisService.save(analysis);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnalysis);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar análisis sectorial existente")
    public ResponseEntity<SectorAnalysis> updateAnalysis(@PathVariable Long id,
                                                         @Valid @RequestBody SectorAnalysis analysis) {
        SectorAnalysis updatedAnalysis = sectorAnalysisService.update(id, analysis);
        return ResponseEntity.ok(updatedAnalysis);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar análisis sectorial")
    public ResponseEntity<Void> deleteAnalysis(@PathVariable Long id) {
        sectorAnalysisService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sector/{sector}")
    @Operation(summary = "Obtener análisis sectoriales por sector")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesBySector(@PathVariable String sector) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findBySector(sector);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/date-range")
    @Operation(summary = "Obtener análisis sectoriales por rango de fechas")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByDateRange(startDate, endDate);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/sector-date-range")
    @Operation(summary = "Obtener análisis sectoriales por sector y rango de fechas")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesBySectorAndDateRange(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findBySectorAndDateRange(sector, startDate, endDate);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/min-reduction/{minPercentage}")
    @Operation(summary = "Obtener análisis sectoriales con porcentaje mínimo de reducción")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByMinReduction(@PathVariable Double minPercentage) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByMinReductionPercentage(minPercentage);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/min-restriction/{minLevel}")
    @Operation(summary = "Obtener análisis sectoriales con nivel mínimo de restricción")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByMinRestriction(@PathVariable Double minLevel) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByMinRestrictionLevel(minLevel);
        return ResponseEntity.ok(analyses);
    }

    @PostMapping("/generate")
    @Operation(summary = "Generar análisis automático para un sector y período")
    public ResponseEntity<SectorAnalysis> generateAnalysis(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        SectorAnalysis analysis = sectorAnalysisService.generateAnalysisForSector(sector, startDate, endDate);
        return ResponseEntity.status(HttpStatus.CREATED).body(analysis);
    }
}


package com.emissions.api.controller;

import com.emissions.api.model.SectorAnalysis;
import com.emissions.api.service.SectorAnalysisService;
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

@RestController
@RequestMapping("/api/sector-analysis")
@Tag(name = "Análisis Sectorial", description = "API para gestionar análisis sectoriales de emisiones")
public class SectorAnalysisController {

    private final SectorAnalysisService sectorAnalysisService;

    @Autowired
    public SectorAnalysisController(SectorAnalysisService sectorAnalysisService) {
        this.sectorAnalysisService = sectorAnalysisService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los análisis sectoriales")
    public ResponseEntity<List<SectorAnalysis>> getAllAnalyses() {
        List<SectorAnalysis> analyses = sectorAnalysisService.findAll();
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener análisis sectorial por ID")
    public ResponseEntity<SectorAnalysis> getAnalysisById(@PathVariable Long id) {
        SectorAnalysis analysis = sectorAnalysisService.findById(id);
        return ResponseEntity.ok(analysis);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo análisis sectorial")
    public ResponseEntity<SectorAnalysis> createAnalysis(@Valid @RequestBody SectorAnalysis analysis) {
        SectorAnalysis savedAnalysis = sectorAnalysisService.save(analysis);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnalysis);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar análisis sectorial existente")
    public ResponseEntity<SectorAnalysis> updateAnalysis(@PathVariable Long id,
                                                         @Valid @RequestBody SectorAnalysis analysis) {
        SectorAnalysis updatedAnalysis = sectorAnalysisService.update(id, analysis);
        return ResponseEntity.ok(updatedAnalysis);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar análisis sectorial")
    public ResponseEntity<Void> deleteAnalysis(@PathVariable Long id) {
        sectorAnalysisService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sector/{sector}")
    @Operation(summary = "Obtener análisis sectoriales por sector")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesBySector(@PathVariable String sector) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findBySector(sector);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/date-range")
    @Operation(summary = "Obtener análisis sectoriales por rango de fechas")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByDateRange(startDate, endDate);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/sector-date-range")
    @Operation(summary = "Obtener análisis sectoriales por sector y rango de fechas")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesBySectorAndDateRange(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findBySectorAndDateRange(sector, startDate, endDate);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/min-reduction/{minPercentage}")
    @Operation(summary = "Obtener análisis sectoriales con porcentaje mínimo de reducción")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByMinReduction(@PathVariable Double minPercentage) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByMinReductionPercentage(minPercentage);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/min-restriction/{minLevel}")
    @Operation(summary = "Obtener análisis sectoriales con nivel mínimo de restricción")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByMinRestriction(@PathVariable Double minLevel) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByMinRestrictionLevel(minLevel);
        return ResponseEntity.ok(analyses);
    }

    @PostMapping("/generate")
    @Operation(summary = "Generar análisis automático para un sector y período")
    public ResponseEntity<SectorAnalysis> generateAnalysis(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        SectorAnalysis analysis = sectorAnalysisService.generateAnalysisForSector(sector, startDate, endDate);
        return ResponseEntity.status(HttpStatus.CREATED).body(analysis);
    }
}


package com.emissions.api.controller;

import com.emissions.api.model.SectorAnalysis;
import com.emissions.api.service.SectorAnalysisService;
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

@RestController
@RequestMapping("/api/sector-analysis")
@Tag(name = "Análisis Sectorial", description = "API para gestionar análisis sectoriales de emisiones")
public class SectorAnalysisController {

    private final SectorAnalysisService sectorAnalysisService;

    @Autowired
    public SectorAnalysisController(SectorAnalysisService sectorAnalysisService) {
        this.sectorAnalysisService = sectorAnalysisService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los análisis sectoriales")
    public ResponseEntity<List<SectorAnalysis>> getAllAnalyses() {
        List<SectorAnalysis> analyses = sectorAnalysisService.findAll();
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener análisis sectorial por ID")
    public ResponseEntity<SectorAnalysis> getAnalysisById(@PathVariable Long id) {
        SectorAnalysis analysis = sectorAnalysisService.findById(id);
        return ResponseEntity.ok(analysis);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo análisis sectorial")
    public ResponseEntity<SectorAnalysis> createAnalysis(@Valid @RequestBody SectorAnalysis analysis) {
        SectorAnalysis savedAnalysis = sectorAnalysisService.save(analysis);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnalysis);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar análisis sectorial existente")
    public ResponseEntity<SectorAnalysis> updateAnalysis(@PathVariable Long id,
                                                         @Valid @RequestBody SectorAnalysis analysis) {
        SectorAnalysis updatedAnalysis = sectorAnalysisService.update(id, analysis);
        return ResponseEntity.ok(updatedAnalysis);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar análisis sectorial")
    public ResponseEntity<Void> deleteAnalysis(@PathVariable Long id) {
        sectorAnalysisService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sector/{sector}")
    @Operation(summary = "Obtener análisis sectoriales por sector")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesBySector(@PathVariable String sector) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findBySector(sector);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/date-range")
    @Operation(summary = "Obtener análisis sectoriales por rango de fechas")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByDateRange(startDate, endDate);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/sector-date-range")
    @Operation(summary = "Obtener análisis sectoriales por sector y rango de fechas")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesBySectorAndDateRange(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findBySectorAndDateRange(sector, startDate, endDate);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/min-reduction/{minPercentage}")
    @Operation(summary = "Obtener análisis sectoriales con porcentaje mínimo de reducción")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByMinReduction(@PathVariable Double minPercentage) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByMinReductionPercentage(minPercentage);
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/min-restriction/{minLevel}")
    @Operation(summary = "Obtener análisis sectoriales con nivel mínimo de restricción")
    public ResponseEntity<List<SectorAnalysis>> getAnalysesByMinRestriction(@PathVariable Double minLevel) {
        List<SectorAnalysis> analyses = sectorAnalysisService.findByMinRestrictionLevel(minLevel);
        return ResponseEntity.ok(analyses);
    }

    @PostMapping("/generate")
    @Operation(summary = "Generar análisis automático para un sector y período")
    public ResponseEntity<SectorAnalysis> generateAnalysis(
            @RequestParam String sector,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        SectorAnalysis analysis = sectorAnalysisService.generateAnalysisForSector(sector, startDate, endDate);
        return ResponseEntity.status(HttpStatus.CREATED).body(analysis);
    }
}


