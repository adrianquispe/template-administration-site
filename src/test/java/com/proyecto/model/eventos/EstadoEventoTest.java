package com.proyecto.model.eventos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class EstadoEventoTest {

    @InjectMocks
    private EstadoEvento estadoEvento;

    @Test
    void testEstadoEventoConstructor() {
        // Verificar que el estado por defecto sea activo=true
        assertTrue(estadoEvento.getActivo());
    }

    @Test
    void testNombre() {
        // Configuración del mock
        String nombreMock = "EstadoMock";
        estadoEvento.setNombre(nombreMock);

        // Verificación de que el nombre se haya establecido correctamente
        assertEquals(nombreMock, estadoEvento.getNombre());
    }

    @Test
    void testActivo() {
        // Configuración del mock
        estadoEvento.setActivo(false);

        // Verificación de que el estado activo se haya establecido correctamente
        assertFalse(estadoEvento.getActivo());
    }
}
