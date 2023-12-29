package com.proyecto.servicios;

import com.proyecto.model.departamento.autoridadDepartamento.RolAutoridad;

import java.util.List;

public interface RolAutoridadServicio {

    public List<RolAutoridad> listarRoles();

    public RolAutoridad obtenerRolPorId(Long id);

    public RolAutoridad guardarRol(RolAutoridad rol);

    public RolAutoridad actualizarRol(RolAutoridad rol);

    public void eliminarRol(RolAutoridad rol);

    public RolAutoridad rolSegunDescripcion(String descripcion);
}
