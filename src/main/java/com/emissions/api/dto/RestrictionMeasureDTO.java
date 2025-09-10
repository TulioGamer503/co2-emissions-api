package com.emissions.api.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class RestrictionMeasureDTO {

    private Long id;

    @NotBlank(message = "El tipo de medida es obligatorio")
    private String measureType;

    @NotBlank(message = "La descripción es obligatoria")
    private String description;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate startDate;

    private LocalDate endDate;

    @Min(value = 0, message = "El nivel de intensidad debe ser entre 0 y 5")
    @Max(value = 5, message = "El nivel de intensidad debe ser entre 0 y 5")
    private Integer intensityLevel;

    @NotBlank(message = "El sector afectado es obligatorio")
    private String affectedSector;

    // Constructores
    public RestrictionMeasureDTO() {}

    public RestrictionMeasureDTO(Long id, String measureType, String description, LocalDate startDate,
                                 LocalDate endDate, Integer intensityLevel, String affectedSector) {
        this.id = id;
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