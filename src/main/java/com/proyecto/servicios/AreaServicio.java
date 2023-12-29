package com.proyecto.servicios;

import com.proyecto.model.Area.Area;

import java.util.List;

public interface AreaServicio {

    public Area obtenerAreaPorId(Long id);

    public List<Area> listarAreas();

    public Area guardarArea(Area area);

    public Area actualizarArea(Area area);

    public void eliminarArea(Area area);
}
