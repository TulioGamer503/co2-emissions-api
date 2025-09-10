package com.emissions.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "restriction_measures")
public class RestrictionMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El tipo de medida es obligatorio")
    @Column(name = "measure_type", nullable = false)
    private String measureType;

    @NotBlank(message = "La descripción es obligatoria")
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Min(value = 0, message = "El nivel de intensidad debe ser entre 0 y 5")
    @Max(value = 5, message = "El nivel de intensidad debe ser entre 0 y 5")
    @Column(name = "intensity_level", nullable = false)
    private Integer intensityLevel;

    @NotBlank(message = "El sector afectado es obligatorio")
    @Column(name = "affected_sector", nullable = false)
    private String affectedSector;

    // Constructores
    public RestrictionMeasure() {}

    public RestrictionMeasure(String measureType, String description, LocalDate startDate,
                              LocalDate endDate, Integer intensityLevel, String affectedSector) {
        this.measureType = measureType;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.intensityLevel = intensityLevel;
        this.affectedSector = affectedSector;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMeasureType() { return measureType; }
    public void setMeasureType(String measureType) { this.measureType = measureType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public Integer getIntensityLevel() { return intensityLevel; }
    public void setIntensityLevel(Integer intensityLevel) { this.intensityLevel = intensityLevel; }

    public String getAffectedSector() { return affectedSector; }
    public void setAffectedSector(String affectedSector) { this.affectedSector = affectedSector; }
}