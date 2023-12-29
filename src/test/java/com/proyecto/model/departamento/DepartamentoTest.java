package com.proyecto.model.departamento;

import com.proyecto.model.Area.Area;
import com.proyecto.model.departamento.autoridadDepartamento.AutoridadDepartamento;
import com.proyecto.model.eventos.Evento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DepartamentoTest {

    private Departamento departamento;

    @BeforeEach
    void setUp() {
        departamento = new Departamento();
    }

    @Test
    void testAgregarDepartamento() {
        Departamento subDepartamento1 = new Departamento();
        Departamento subDepartamento2 = new Departamento();

        departamento.agregarDepartamento(subDepartamento1, subDepartamento2);

        List<Departamento> departamentos = departamento.getDepartamentos();
        assertEquals(2, departamentos.size());
        assertTrue(departamentos.contains(subDepartamento1));
        assertTrue(departamentos.contains(subDepartamento2));
    }

    @Test
    void testAgregarAreas() {
        Area area1 = new Area();
        Area area2 = new Area();

        departamento.agregarAreas(area1, area2);

        List<Area> areas = departamento.getAreas();
        assertEquals(2, areas.size());
        assertTrue(areas.contains(area1));
        assertTrue(areas.contains(area2));
    }

    @Test
    void testAgregarAutoridades() {
        AutoridadDepartamento autoridad1 = new AutoridadDepartamento();
        AutoridadDepartamento autoridad2 = new AutoridadDepartamento();

        departamento.agregarAutoridades(autoridad1, autoridad2);

        List<AutoridadDepartamento> autoridades = departamento.getAutoridades();
        assertEquals(2, autoridades.size());
        assertTrue(autoridades.contains(autoridad1));
        assertTrue(autoridades.contains(autoridad2));
    }

    @Test
    void testAgregarEvento() {
        Evento evento = new Evento();

        departamento.agregarEvento(evento);

        List<Evento> eventos = departamento.getEventos();
        assertEquals(1, eventos.size());
        assertEquals(departamento, evento.getDepartamentoOrganizador());
    }
}
