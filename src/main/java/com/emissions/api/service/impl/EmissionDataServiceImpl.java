package com.emissions.api.service.impl;

import com.emissions.api.model.EmissionData;
import com.emissions.api.repository.EmissionDataRepository;
import com.emissions.api.service.EmissionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmissionDataServiceImpl implements EmissionDataService {

    private final EmissionDataRepository emissionDataRepository;

    @Autowired
    public EmissionDataServiceImpl(EmissionDataRepository emissionDataRepository) {
        this.emissionDataRepository = emissionDataRepository;
    }

    @Override
    public List<EmissionData> findAll() {
        return emissionDataRepository.findAll();
    }

    @Override
    public EmissionData findById(Long id) {
        return emissionDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Datos de emisión no encontrados con ID: " + id));
    }

    @Override
    public EmissionData save(EmissionData emissionData) {
        return emissionDataRepository.save(emissionData);
    }

    @Override
    public EmissionData update(Long id, EmissionData emissionData) {
        EmissionData existingData = findById(id);

        existingData.setMeasurementDate(emissionData.getMeasurementDate());
        existingData.setSector(emissionData.getSector());
        existingData.setCo2Emissions(emissionData.getCo2Emissions());
        existingData.setRestrictionLevel(emissionData.getRestrictionLevel());
        existingData.setDataSource(emissionData.getDataSource());

        return emissionDataRepository.save(existingData);
    }

    @Override
    public void delete(Long id) {
        EmissionData emissionData = findById(id);
        emissionDataRepository.delete(emissionData);
    }

    @Override
    public List<EmissionData> findBySector(String sector) {
        return emissionDataRepository.findBySector(sector);
    }

    @Override
    public List<EmissionData> findByDateRange(LocalDate startDate, LocalDate endDate) {
        return emissionDataRepository.findByMeasurementDateBetween(startDate, endDate);
    }

    @Override
    public List<EmissionData> findBySectorAndDateRange(String sector, LocalDate startDate, LocalDate endDate) {
        return emissionDataRepository.findBySectorAndMeasurementDateBetween(sector, startDate, endDate);
    }

    @Override
    public List<EmissionData> findByRestrictionLevel(Integer restrictionLevel) {
        return emissionDataRepository.findByRestrictionLevel(restrictionLevel);
    }

    @Override
    public List<EmissionData> findByYear(int year) {
        return emissionDataRepository.findByYear(year);
    }

    @Override
    public Map<String, Double> getAverageEmissionsBySector(LocalDate startDate, LocalDate endDate) {
        List<Object[]> results = emissionDataRepository.findAverageEmissionsBySector(startDate, endDate);
        Map<String, Double> averages = new HashMap<>();

        for (Object[] result : results) {
            String sector = (String) result[0];
            Double average = (Double) result[1];
            averages.put(sector, average);
        }

        return averages;
    }

    @Override
    public Map<String, Double> getReductionPercentageBySector(LocalDate pandemicStart, LocalDate pandemicEnd,
                                                              LocalDate baselineStart, LocalDate baselineEnd) {
        Map<String, Double> pandemicAverages = getAverageEmissionsBySector(pandemicStart, pandemicEnd);
        Map<String, Double> baselineAverages = getAverageEmissionsBySector(baselineStart, baselineEnd);

        Map<String, Double> reductionPercentages = new HashMap<>();

        for (String sector : baselineAverages.keySet()) {
            if (pandemicAverages.containsKey(sector)) {
                double baseline = baselineAverages.get(sector);
                double pandemic = pandemicAverages.get(sector);
                double reduction = ((baseline - pandemic) / baseline) * 100;
                reductionPercentages.put(sector, reduction);
            }
        }

        return reductionPercentages;
    }
}