package com.emissions.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "emission_data")
public class EmissionData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha es obligatoria")
    @Column(name = "measurement_date", nullable = false)
    private LocalDate measurementDate;

    @NotBlank(message = "El sector es obligatorio")
    @Column(nullable = false)
    private String sector;

    @NotNull(message = "Las emisiones de CO2 son obligatorias")
    @PositiveOrZero(message = "Las emisiones deben ser positivas o cero")
    @Column(name = "co2_emissions", nullable = false)
    private Double co2Emissions;

    @Column(name = "restriction_level")
    @Min(value = 0, message = "El nivel de restricción debe ser entre 0 y 5")
    @Max(value = 5, message = "El nivel de restricción debe ser entre 0 y 5")
    private Integer restrictionLevel;

    @Column(name = "data_source")
    private String dataSource;

    // Constructores
    public EmissionData() {}

    public EmissionData(LocalDate measurementDate, String sector, Double co2Emissions,
                        Integer restrictionLevel, String dataSource) {
        this.measurementDate = measurementDate;
        this.sector = sector;
        this.co2Emissions = co2Emissions;
        this.restrictionLevel = restrictionLevel;
        this.dataSource = dataSource;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getMeasurementDate() { return measurementDate; }
    public void setMeasurementDate(LocalDate measurementDate) { this.measurementDate = measurementDate; }

    public String getSector() { return sector; }
    public void setSector(String sector) { this.sector = sector; }

    public Double getCo2Emissions() { return co2Emissions; }
    public void setCo2Emissions(Double co2Emissions) { this.co2Emissions = co2Emissions; }

    public Integer getRestrictionLevel() { return restrictionLevel; }
    public void setRestrictionLevel(Integer restrictionLevel) { this.restrictionLevel = restrictionLevel; }

    public String getDataSource() { return dataSource; }
    public void setDataSource(String dataSource) { this.dataSource = dataSource; }
}