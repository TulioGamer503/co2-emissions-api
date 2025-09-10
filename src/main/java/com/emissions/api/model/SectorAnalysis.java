package com.emissions.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "sector_analysis")
public class SectorAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El sector es obligatorio")
    @Column(nullable = false)
    private String sector;

    @NotNull(message = "La fecha de análisis es obligatoria")
    @Column(name = "analysis_date", nullable = false)
    private LocalDate analysisDate;

    @NotNull(message = "Las emisiones promedio son obligatorias")
    @PositiveOrZero(message = "Las emisiones deben ser positivas o cero")
    @Column(name = "average_emissions", nullable = false)
    private Double averageEmissions;

    @NotNull(message = "El porcentaje de reducción es obligatorio")
    @Column(name = "reduction_percentage", nullable = false)
    private Double reductionPercentage;

    @Min(value = 0, message = "El nivel de restricción promedio debe ser entre 0 y 5")
    @Max(value = 5, message = "El nivel de restricción promedio debe ser entre 0 y 5")
    @Column(name = "average_restriction_level")
    private Double averageRestrictionLevel;

    @Column(columnDefinition = "TEXT")
    private String observations;

    // Constructores
    public SectorAnalysis() {}

    public SectorAnalysis(String sector, LocalDate analysisDate, Double averageEmissions,
                          Double reductionPercentage, Double averageRestrictionLevel, String observations) {
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