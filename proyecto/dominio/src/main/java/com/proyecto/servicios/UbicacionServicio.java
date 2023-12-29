package com.proyecto.servicios;

import com.proyecto.model.socio.ubicacion.Ubicacion;

public interface UbicacionServicio {

    public Ubicacion obtenerUbicacionPorId(Long id);

    public Ubicacion guardarUbicacion(Ubicacion ubicacion);

    public Ubicacion actualizarUbicacion(Ubicacion ubicacion);

    public Ubicacion obtenerUbicacionVirtual();
}
