package com.proyecto.servicios;

import com.proyecto.model.socio.ubicacion.Ubicacion;
import com.proyecto.persistencia.repositorios.UbicacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UbicacionServicioImpl implements UbicacionServicio{

    @Autowired
    private UbicacionRepositorio repositorio;

    @Override
    public Ubicacion obtenerUbicacionPorId(Long id) {
        return repositorio.getReferenceById(id);
    }

    @Override
    public Ubicacion guardarUbicacion(Ubicacion ubicacion) {
        return repositorio.save(ubicacion);
    }

    @Override
    public Ubicacion actualizarUbicacion(Ubicacion ubicacion) {
        return repositorio.save(ubicacion);
    }

    @Override
    public Ubicacion obtenerUbicacionVirtual() {
        return repositorio.ubicacionVirtual();
    }
}
