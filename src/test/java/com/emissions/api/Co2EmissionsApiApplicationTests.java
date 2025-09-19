package com.emissions.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.emissions.api.model.EmissionData;
import com.emissions.api.repository.EmissionDataRepository;
import com.emissions.api.service.impl.EmissionDataServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
/**
 * Pruebas unitarias para EmissionDataServiceImpl usando Mockito.
 * Cubre operaciones CRUD: listar, buscar por ID, guardar, actualizar y eliminar.
 */
class Co2EmissionsApiApplicationTests {
    @Mock
    private EmissionDataRepository emissionDataRepository;

    @InjectMocks
    private EmissionDataServiceImpl emissionDataService;

    private EmissionData emissionData;

    /**
     * Datos de prueba comunes para los casos: entidad base con ID asignado.
     */
    @BeforeEach
    void setUp() {
        emissionData = new EmissionData(
                LocalDate.of(2020, 4, 1),
                "Transporte",
                150.5,
                3,
                "MARN"
        );
        emissionData.setId(1L);
    }

    /**
     * Debe retornar la lista completa desde el repositorio.
     * Verifica tamaño y que se llame findAll() una vez.
     */
    @Test
    void testFindAll() {
        // Given
        when(emissionDataRepository.findAll()).thenReturn(Arrays.asList(emissionData));

        // When
        List<EmissionData> result = emissionDataService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(emissionDataRepository, times(1)).findAll();
    }

    /**
     * Debe recuperar una entidad por ID y devolverla con sus datos.
     * Verifica sector y llamada a findById().
     */
    @Test
    void testFindById() {
        // Given
        when(emissionDataRepository.findById(1L)).thenReturn(Optional.of(emissionData));

        // When
        EmissionData result = emissionDataService.findById(1L);

        // Then
        assertNotNull(result);
        assertEquals("Transporte", result.getSector());
        verify(emissionDataRepository, times(1)).findById(1L);
    }

    /**
     * Debe persistir una nueva entidad y retornarla con su ID.
     * Verifica llamada a save() y contenido básico.
     */
    @Test
    void testSave() {
        // Given
        when(emissionDataRepository.save(any(EmissionData.class))).thenReturn(emissionData);

        // When
        EmissionData result = emissionDataService.save(emissionData);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(emissionDataRepository, times(1)).save(emissionData);
    }

    /**
     * Debe actualizar campos de la entidad existente y devolver el resultado.
     * Verifica cambios de sector y emisiones, y llamadas a findById() y save().
     */
    @Test
    void testUpdate() {
        // Given
        EmissionData updatedData = new EmissionData(
                LocalDate.of(2020, 4, 1),
                "Transporte Actualizado",
                120.0,
                4,
                "MARN Actualizado"
        );

        when(emissionDataRepository.findById(1L)).thenReturn(Optional.of(emissionData));
        when(emissionDataRepository.save(any(EmissionData.class))).thenReturn(updatedData);

        // When
        EmissionData result = emissionDataService.update(1L, updatedData);

        // Then
        assertNotNull(result);
        assertEquals("Transporte Actualizado", result.getSector());
        assertEquals(120.0, result.getCo2Emissions());
        verify(emissionDataRepository, times(1)).findById(1L);
        verify(emissionDataRepository, times(1)).save(any(EmissionData.class));
    }

    /**
     * Debe eliminar una entidad existente sin errores.
     * Verifica llamadas a findById() y delete().
     */
    @Test
    void testDelete() {
        // Given
        when(emissionDataRepository.findById(1L)).thenReturn(Optional.of(emissionData));
        doNothing().when(emissionDataRepository).delete(emissionData);

        // When
        emissionDataService.delete(1L);

        // Then
        verify(emissionDataRepository, times(1)).findById(1L);
        verify(emissionDataRepository, times(1)).delete(emissionData);
    }

}
