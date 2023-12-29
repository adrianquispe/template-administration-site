package com.proyecto.servicios;

import com.proyecto.model.departamento.autoridadDepartamento.RolAutoridad;
import com.proyecto.persistencia.repositorios.RolAutoridadRepositorio;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolAutoridadServicioImpl implements RolAutoridadServicio{

    @Autowired
    private RolAutoridadRepositorio repositorio;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<RolAutoridad> listarRoles(){
        return repositorio.findAll();
    }

    @Override
    public RolAutoridad obtenerRolPorId(Long id) {
        return repositorio.findById(id).get();
    }

    @Override
    public RolAutoridad guardarRol(RolAutoridad rol) {
        return repositorio.save(rol);
    }

    @Override
    public RolAutoridad actualizarRol(RolAutoridad rol) {
        return repositorio.save(repositorio.findById(rol.getId()).get());
    }

    @Override
    public void eliminarRol(RolAutoridad rol) {
        repositorio.deleteById(rol.getId());
    }


    public List rolesSegunEstado(Boolean activo){
        return entityManager
                .createQuery("from RolAutoridad where activo = :activo")
                .setParameter("activo", activo)
                .getResultList();
    }

    public RolAutoridad rolSegunDescripcion(String descripcion){
        return (RolAutoridad) entityManager
                .createQuery("from RolAutoridad where descripcion = :descripcion")
                .setParameter("descripcion",descripcion)
                .getSingleResult();
    }
}
