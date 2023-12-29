package com.proyecto.servicios;


import com.proyecto.model.departamento.autoridadDepartamento.AutoridadDepartamento;
import com.proyecto.persistencia.repositorios.AutoridadDepartamentoRepositorio;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoridadDepartamentoServicioImpl implements AutoridadDepartamentoServicio {

    @Autowired
    private AutoridadDepartamentoRepositorio repositorio;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<AutoridadDepartamento> listaAutoridadesDepartamentos() {
        return repositorio.findAll();
    }

    @Override
    public AutoridadDepartamento guardarAutoridadDepartamento(AutoridadDepartamento autoridadDepartamento){
        return repositorio.save(autoridadDepartamento);
    }
    @Override
    public AutoridadDepartamento obtenerAutoridadDepartamentoPorId(long id){
        return repositorio.findById(id).get();
    }

    @Override
    public AutoridadDepartamento actualizarAutoridadDepartamento(AutoridadDepartamento autoridadDepartamento){
        return repositorio.save(autoridadDepartamento);
    }

    @Override
    public void eliminarAutoridadDepartamento(Long id){
        repositorio.deleteById(id);
    }


    public List<AutoridadDepartamento> autoridadesSegunEstado(Boolean activo){
        return entityManager
                .createQuery("from AutoridadDepartamento where activo = :activo")
                .setParameter("activo", activo)
                .getResultList();
    }
}
