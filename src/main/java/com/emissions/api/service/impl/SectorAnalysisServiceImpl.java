package com.emissions.api.service.impl;

import com.emissions.api.model.EmissionData;
import com.emissions.api.model.SectorAnalysis;
import com.emissions.api.repository.EmissionDataRepository;
import com.emissions.api.repository.SectorAnalysisRepository;
import com.emissions.api.service.SectorAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SectorAnalysisServiceImpl implements SectorAnalysisService {

    private final SectorAnalysisRepository sectorAnalysisRepository;
    private final EmissionDataRepository emissionDataRepository;

    @Autowired
    public SectorAnalysisServiceImpl(SectorAnalysisRepository sectorAnalysisRepository,
                                     EmissionDataRepository emissionDataRepository) {
        this.sectorAnalysisRepository = sectorAnalysisRepository;
        this.emissionDataRepository = emissionDataRepository;
    }

    @Override
    public List<SectorAnalysis> findAll() {
        return sectorAnalysisRepository.findAll();
    }

    @Override
    public SectorAnalysis findById(Long id) {
        return sectorAnalysisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Análisis sectorial no encontrado con ID: " + id));
    }

    @Override
    public SectorAnalysis save(SectorAnalysis sectorAnalysis) {
        return sectorAnalysisRepository.save(sectorAnalysis);
    }

    @Override
    public SectorAnalysis update(Long id, SectorAnalysis sectorAnalysis) {
        SectorAnalysis existingAnalysis = findById(id);

        existingAnalysis.setSector(sectorAnalysis.getSector());
        existingAnalysis.setAnalysisDate(sectorAnalysis.getAnalysisDate());
        existingAnalysis.setAverageEmissions(sectorAnalysis.getAverageEmissions());
        existingAnalysis.setReductionPercentage(sectorAnalysis.getReductionPercentage());
        existingAnalysis.setAverageRestrictionLevel(sectorAnalysis.getAverageRestrictionLevel());
        existingAnalysis.setObservations(sectorAnalysis.getObservations());

        return sectorAnalysisRepository.save(existingAnalysis);
    }

    @Override
    public void delete(Long id) {
        SectorAnalysis sectorAnalysis = findById(id);
        sectorAnalysisRepository.delete(sectorAnalysis);
    }

    @Override
    public List<SectorAnalysis> findBySector(String sector) {
        return sectorAnalysisRepository.findBySector(sector);
    }

    @Override
    public List<SectorAnalysis> findByDateRange(LocalDate startDate, LocalDate endDate) {
        return sectorAnalysisRepository.findByAnalysisDateBetween(startDate, endDate);
    }

    @Override
    public List<SectorAnalysis> findBySectorAndDateRange(String sector, LocalDate startDate, LocalDate endDate) {
        return sectorAnalysisRepository.findBySectorAndAnalysisDateBetween(sector, startDate, endDate);
    }

    @Override
    public List<SectorAnalysis> findByMinReductionPercentage(Double minPercentage) {
        return sectorAnalysisRepository.findByMinReductionPercentage(minPercentage);
    }

    @Override
    public List<SectorAnalysis> findByMinRestrictionLevel(Double minLevel) {
        return sectorAnalysisRepository.findByMinRestrictionLevel(minLevel);
    }

    @Override
    public SectorAnalysis generateAnalysisForSector(String sector, LocalDate startDate, LocalDate endDate) {
        List<EmissionData> emissions = emissionDataRepository.findBySectorAndMeasurementDateBetween(sector, startDate, endDate);

        if (emissions.isEmpty()) {
            throw new RuntimeException("No hay datos de emisiones para el sector " + sector + " en el rango de fechas especificado");
        }

        double totalEmissions = 0;
        double totalRestrictionLevel = 0;

        for (EmissionData emission : emissions) {
            totalEmissions += emission.getCo2Emissions();
            if (emission.getRestrictionLevel() != null) {
                totalRestrictionLevel += emission.getRestrictionLevel();
            }
        }

        double averageEmissions = totalEmissions / emissions.size();
        double averageRestrictionLevel = totalRestrictionLevel / emissions.size();

        // Calcular reducción porcentual (simplificado - en un caso real se compararía con datos de referencia)
        double reductionPercentage = calculateReductionPercentage(sector, averageEmissions);

        SectorAnalysis analysis = new SectorAnalysis();
        analysis.setSector(sector);
        analysis.setAnalysisDate(LocalDate.now());
        analysis.setAverageEmissions(averageEmissions);
        analysis.setReductionPercentage(reductionPercentage);
        analysis.setAverageRestrictionLevel(averageRestrictionLevel);
        analysis.setObservations("Análisis generado automáticamente para el período " + startDate + " a " + endDate);

        return sectorAnalysisRepository.save(analysis);
    }

    private double calculateReductionPercentage(String sector, double currentAverage) {
        // En una implementación real, esto compararía con datos históricos de referencia
        // Por ahora, usamos valores simplificados para demostración
        switch (sector.toLowerCase()) {
            case "transporte":
                return 25.0; // 25% de reducción estimada
            case "industria":
                return 15.0; // 15% de reducción estimada
            case "energía":
                return 10.0; // 10% de reducción estimada
            default:
                return 5.0;  // 5% de reducción estimada para otros sectores
        }
    }
}