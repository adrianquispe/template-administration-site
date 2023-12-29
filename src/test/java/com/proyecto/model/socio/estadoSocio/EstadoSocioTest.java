package com.proyecto.model.socio.estadoSocio;

import org.junit.Test;
import static org.junit.Assert.*;

import com.proyecto.model.socio.Socio;
import java.time.LocalDate;

public class EstadoSocioTest {

    @Test
    public void EstadoSocioConstructorTest() {
        EstadoSocio estadoSocio = new EstadoSocio();
        assertNotNull(estadoSocio);
    }

    @Test
    public void testEstadoSocioSettersAndGetters() {
        EstadoSocio estadoSocio = new EstadoSocio();
        estadoSocio.setFecha(LocalDate.now());
        estadoSocio.setActivo(true);

        assertEquals(LocalDate.now(), estadoSocio.getFecha());
        assertTrue(estadoSocio.getActivo());
    }

    @Test
    public void testEstadoSocioSocioAssociation() {
        EstadoSocio estadoSocio = new EstadoSocio();
        Socio socio = new Socio(); // Asume que tienes una clase Socio

        estadoSocio.setSocio(socio);
        assertSame(socio, estadoSocio.getSocio());
    }
}