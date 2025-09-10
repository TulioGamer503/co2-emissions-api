package com.emissions.api.service.impl;

import com.emissions.api.model.RestrictionMeasure;
import com.emissions.api.repository.RestrictionMeasureRepository;
import com.emissions.api.service.RestrictionMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RestrictionMeasureServiceImpl implements RestrictionMeasureService {

    private final RestrictionMeasureRepository restrictionMeasureRepository;

    @Autowired
    public RestrictionMeasureServiceImpl(RestrictionMeasureRepository restrictionMeasureRepository) {
        this.restrictionMeasureRepository = restrictionMeasureRepository;
    }

    @Override
    public List<RestrictionMeasure> findAll() {
        return restrictionMeasureRepository.findAll();
    }

    @Override
    public RestrictionMeasure findById(Long id) {
        return restrictionMeasureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medida de restricción no encontrada con ID: " + id));
    }

    @Override
    public RestrictionMeasure save(RestrictionMeasure restrictionMeasure) {
        return restrictionMeasureRepository.save(restrictionMeasure);
    }

    @Override
    public RestrictionMeasure update(Long id, RestrictionMeasure restrictionMeasure) {
        RestrictionMeasure existingMeasure = findById(id);

        existingMeasure.setMeasureType(restrictionMeasure.getMeasureType());
        existingMeasure.setDescription(restrictionMeasure.getDescription());
        existingMeasure.setStartDate(restrictionMeasure.getStartDate());
        existingMeasure.setEndDate(restrictionMeasure.getEndDate());
        existingMeasure.setIntensityLevel(restrictionMeasure.getIntensityLevel());
        existingMeasure.setAffectedSector(restrictionMeasure.getAffectedSector());

        return restrictionMeasureRepository.save(existingMeasure);
    }

    @Override
    public void delete(Long id) {
        RestrictionMeasure restrictionMeasure = findById(id);
        restrictionMeasureRepository.delete(restrictionMeasure);
    }

    @Override
    public List<RestrictionMeasure> findByAffectedSector(String sector) {
        return restrictionMeasureRepository.findByAffectedSector(sector);
    }

    @Override
    public List<RestrictionMeasure> findByIntensityLevel(Integer intensityLevel) {
        return restrictionMeasureRepository.findByIntensityLevel(intensityLevel);
    }

    @Override
    public List<RestrictionMeasure> findByDateRange(LocalDate startDate, LocalDate endDate) {
        return restrictionMeasureRepository.findByStartDateBetween(startDate, endDate);
    }

    @Override
    public List<RestrictionMeasure> findBySectorAndDateRange(String sector, LocalDate startDate, LocalDate endDate) {
        return restrictionMeasureRepository.findBySectorAndDateRange(sector, startDate, endDate);
    }

    @Override
    public List<RestrictionMeasure> findByMinIntensityLevel(Integer minLevel) {
        return restrictionMeasureRepository.findByMinIntensityLevel(minLevel);
    }
}