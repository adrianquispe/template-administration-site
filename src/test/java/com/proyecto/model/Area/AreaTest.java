package com.proyecto.model.Area;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import com.proyecto.persistencia.Persistencia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class AreaTest {

    @Mock
    private Persistencia persistenciaMock;

    @InjectMocks
    private Area area;

    private Validator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testSetNombre() {
        // Given
        String nombre = "Área de Prueba";
        area.setNombre(nombre);

        // When
        Set<ConstraintViolation<Area>> violations = validator.validate(area);

        // Then
        assertTrue(violations.isEmpty(), "Violations should be empty");
        assertEquals(nombre, area.getNombre(), "Nombre should be set correctly");
    }

    @Test
    void testConstructor() {
        // Given
        Long idArea = 1L;
        String nombre = "Área de Prueba";
        Area area = new Area(idArea, nombre);

        // When
        when(persistenciaMock.getId()).thenReturn(idArea);

        // Then
        assertEquals(idArea, area.getId(), "Id should be set correctly");
        assertEquals(nombre, area.getNombre(), "Nombre should be set correctly");
    }
}


