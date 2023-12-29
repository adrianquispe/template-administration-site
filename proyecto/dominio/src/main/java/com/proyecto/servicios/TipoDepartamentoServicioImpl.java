package com.proyecto.servicios;

import com.proyecto.model.departamento.TipoDepartamento;
import com.proyecto.persistencia.repositorios.TipoDepartamentoRepositorio;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoDepartamentoServicioImpl implements TipoDepartamentoServicio {

    @Autowired
    private TipoDepartamentoRepositorio repositorio;

    @Autowired
    private EntityManager entityManager;


    @Override
    public List<TipoDepartamento> listarTipos() {
        return repositorio.findAll();
    }

    @Override
    public TipoDepartamento guardarTipo(TipoDepartamento tipo) {
        return repositorio.save(tipo);
    }

    @Override
    public TipoDepartamento obtenerTipoPorID(Long id) {
        return repositorio.findById(id).get();
    }

    @Override
    public TipoDepartamento actualizarTipo(TipoDepartamento tipo) {
        return repositorio.save(tipo);
    }

    @Override
    public Page<TipoDepartamento> listarTipos(Pageable pagina) {
        return repositorio.findAll(pagina);
    }

    public List<TipoDepartamento> obtenerTipoPorEstado(Boolean activo){
        return entityManager
                .createQuery("from TipoDepartamento where activo = :activo")
                .setParameter("activo",activo)
                .getResultList();
    }

    @Override
    public TipoDepartamento obtenerTipoPorNombre(String nombre) {
        return (TipoDepartamento) entityManager
                .createQuery("from TipoDepartamento where nombre = :nombre")
                .setParameter("nombre", nombre)
                .getSingleResult();
    }
}
