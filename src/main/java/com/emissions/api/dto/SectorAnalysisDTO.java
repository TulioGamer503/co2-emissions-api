package com.emissions.api.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class SectorAnalysisDTO {

    private Long id;

    @NotBlank(message = "El sector es obligatorio")
    private String sector;

    @NotNull(message = "La fecha de análisis es obligatoria")
    private LocalDate analysisDate;

    @NotNull(message = "Las emisiones promedio son obligatorias")
    @PositiveOrZero(message = "Las emisiones deben ser positivas o cero")
    private Double averageEmissions;

    @NotNull(message = "El porcentaje de reducción es obligatorio")
    private Double reductionPercentage;

    @Min(value = 0, message = "El nivel de restricción promedio debe ser entre 0 y 5")
    @Max(value = 5, message = "El nivel de restricción promedio debe ser entre 0 y 5")
    private Double averageRestrictionLevel;

    private String observations;

    // Constructores
    public SectorAnalysisDTO() {}

    public SectorAnalysisDTO(Long id, String sector, LocalDate analysisDate, Double averageEmissions,
                             Double reductionPercentage, Double averageRestrictionLevel, String observations) {
        this.id = id;
        this.sector = sector;
        this.analysisDate = analysisDate;
        this.averageEmissions = averageEmissions;
        this.reductionPercentage = reductionPercentage;
        this.averageRestrictionLevel = averageRestrictionLevel;
        this.observations = observations;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSector() { return sector; }
    public void setSector(String sector) { this.sector = sector; }

    public LocalDate getAnalysisDate() { return analysisDate; }
    public void setAnalysisDate(LocalDate analysisDate) { this.analysisDate = analysisDate; }

    public Double getAverageEmissions() { return averageEmissions; }
    public void setAverageEmissions(Double averageEmissions) { this.averageEmissions = averageEmissions; }

    public Double getReductionPercentage() { return reductionPercentage; }
    public void setReductionPercentage(Double reductionPercentage) { this.reductionPercentage = reductionPercentage; }

    public Double getAverageRestrictionLevel() { return averageRestrictionLevel; }
    public void setAverageRestrictionLevel(Double averageRestrictionLevel) { this.averageRestrictionLevel = averageRestrictionLevel; }

    public String getObservations() { return observations; }
    public void setObservations(String observations) { this.observations = observations; }
}