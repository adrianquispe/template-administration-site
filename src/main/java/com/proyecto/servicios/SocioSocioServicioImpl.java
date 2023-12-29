package com.proyecto.servicios;

import com.proyecto.persistencia.repositorios.SocioRepositorio;
import com.proyecto.model.socio.Socio;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocioSocioServicioImpl implements SocioServicio {

    @Autowired
    private SocioRepositorio repositorio;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Socio> listaSocios() {
        return repositorio.findAll();
    }

    @Override
    public Socio guardarSocio(Socio socio) {
        return repositorio.save(socio);
    }

    @Override
    public Socio obtenerSocioPorId(long id) {
        return repositorio.findById(id).get();
    }

    @Override
    public Socio actualizarSocio(Socio socio) {
        return repositorio.save(socio);
    }

    @Override
    public void eliminarSocio(Long id) {
        repositorio.deleteById(id);
    }

    public List<Socio> sociosPorEstado(Boolean activo){
        return entityManager
                .createQuery("from Socio where activo = :activo")
                .setParameter("activo", activo)
                .getResultList();
    }
}
