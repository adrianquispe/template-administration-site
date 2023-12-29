package com.proyecto.servicios;

import com.proyecto.model.espaciosColaborativos.Recurso;
import com.proyecto.persistencia.repositorios.RecursoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecursoServicioImpl implements RecursoServicio{

    @Autowired
    private RecursoRepositorio repositorio;

    @Override
    public Recurso obtenerRecursoPorId(Long id) {
        return repositorio.findById(id).get();
    }
    @Override
    public List<Recurso> listarRecurso(){
        return repositorio.findAll();
    }

    @Override
    public Recurso guardarRecurso(Recurso recurso) {
        return repositorio.save(recurso);
    }

    @Override
    public Recurso actualizarRecurso(Recurso recurso) {
        return repositorio.save(recurso);
    }

    @Override
    public void cancelarRecurso(Long id) {
        repositorio.deleteById(id);
    }
}
