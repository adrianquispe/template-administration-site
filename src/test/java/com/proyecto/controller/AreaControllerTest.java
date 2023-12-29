package com.proyecto.controller;

import com.proyecto.model.Area.Area;
import com.proyecto.servicios.AreaServicioImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AreaControllerTest {

    @Mock
    private AreaServicioImpl servicio;

    @Mock
    private Model modelo;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private AreaController areaController;

    @Test
    void testListarAreas() {
        when(servicio.listarAreas()).thenReturn(Collections.emptyList());

        String viewName = areaController.listarAreas(modelo);

        assertEquals("areas", viewName);
        verify(modelo).addAttribute(eq("areas"), any());
        verify(servicio).listarAreas();
    }

    @Test
    void testMostrarFormularioRegistroArea() {
        String viewName = areaController.mostrarFormularioRegistroArea(modelo);

        assertEquals("crear_area", viewName);
        verify(modelo).addAttribute(eq("area"), any());
    }

    @Test
    void testGuardarAreaWithErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = areaController.guardarArea(modelo, new Area(), bindingResult);

        assertEquals("crear_area", viewName);
        verify(servicio, never()).guardarArea(any());
    }

    @Test
    void testGuardarAreaWithoutErrors() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = areaController.guardarArea(modelo, new Area(), bindingResult);

        assertEquals("redirect:areas", viewName);
        verify(servicio).guardarArea(any());
    }
}


