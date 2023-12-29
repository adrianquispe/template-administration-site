package com.proyecto.model.socio.ubicacion;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UbicacionTest {


    @Test
    public void UbicacionTest() {
        Ubicacion ubicacion = new Ubicacion();
        /*  */
        ubicacion.setProvincia("Buenos Aires");
        ubicacion.setDepartamento("La Plata");
        ubicacion.setMunicipio("La Plata");
        ubicacion.setCalle("Calle 123");
        ubicacion.setAltura("456");
        ubicacion.setCodigoPostal("1900");
        ubicacion.setPiso("2");
        ubicacion.setBarrio("Barrio X");

        //

        assertEquals("Buenos Aires", ubicacion.getProvincia());
        assertEquals("La Plata", ubicacion.getDepartamento());
        assertEquals("La Plata", ubicacion.getMunicipio());
        assertEquals("Calle 123", ubicacion.getCalle());
        assertEquals("456", ubicacion.getAltura());
        assertEquals("1900", ubicacion.getCodigoPostal());
        assertEquals("2", ubicacion.getPiso());
        assertEquals("Barrio X", ubicacion.getBarrio());
    }
}