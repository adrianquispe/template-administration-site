package com.proyecto.model.departamento;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TipoDepartamentoTest {

    @Test
    public void testAgregarDepartamento() {
        // Crear instancias necesarias
        TipoDepartamento tipoDepartamento = new TipoDepartamento();
        Departamento departamento = new Departamento();

        // Agregar el departamento al tipo de departamento
        tipoDepartamento.agregarDepartamento(departamento);

        // Verificar que el departamento se haya agregado correctamente
        assertTrue(tipoDepartamento.getDepartamentos().contains(departamento));
        assertEquals(tipoDepartamento, departamento.getTipo());
    }

    @Test
    public void testConstructor() {
        // Crear una instancia del tipo de departamento utilizando el constructor por defecto
        TipoDepartamento tipoDepartamento = new TipoDepartamento();

        // Verificar que los valores por defecto se hayan establecido correctamente
        assertTrue(tipoDepartamento.getActivo());
        assertNotNull(tipoDepartamento.getDepartamentos());
        assertNotNull(tipoDepartamento.getAutoridades());
    }

    @Test
    public void testSetterGetter() {
        // Crear una instancia del tipo de departamento
        TipoDepartamento tipoDepartamento = new TipoDepartamento();

        // Establecer y obtener el objetivo
        tipoDepartamento.setObjetivo("Nuevo objetivo");
        assertEquals("Nuevo objetivo", tipoDepartamento.getObjetivo());
    }

    // Puedes agregar más pruebas según tus necesidades
}
