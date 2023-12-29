package com.proyecto.servicios;

import com.proyecto.model.Area.Area;
import com.proyecto.persistencia.repositorios.AreaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServicioImpl implements AreaServicio{

    @Autowired
    AreaRepositorio repositorio;

    @Override
    public Area obtenerAreaPorId(Long id) {
        return repositorio.findById(id).get();
    }

    @Override
    public List<Area> listarAreas() {
        return repositorio.findAll();
    }

    @Override
    public Area guardarArea(Area area) {
        return repositorio.save(area);
    }

    @Override
    public Area actualizarArea(Area area) {
        return repositorio.save(area);
    }

    @Override
    public void eliminarArea(Area area) {
        repositorio.delete(area);
    }
}
