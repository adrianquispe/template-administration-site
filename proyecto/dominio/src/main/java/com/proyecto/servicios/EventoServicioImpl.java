package com.proyecto.servicios;

import com.proyecto.model.departamento.Departamento;
import com.proyecto.model.eventos.Evento;
import com.proyecto.persistencia.repositorios.EventoRepositorio;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventoServicioImpl implements EventoServicio{

    @Autowired
    private EventoRepositorio repositorio;

    @Override
    public Evento obtenerEventoPorId(UUID id) {
        return repositorio.findById(id).get();
    }
    @Override
    public List<Evento> listarEventos(){
        return repositorio.findAll();
    }
    
    @Override
    public Evento guardarEvento(Evento evento) {
        return repositorio.save(evento);
    }

    @Override
    public Evento actualizarEvento(Evento evento) {
        return repositorio.save(evento);
    }

    @Override
    public void cancelarEvento(UUID id) {
        repositorio.deleteById(id);
    }

    public List<Evento> listarEventosPorDepartamento(Departamento departamento) {
        return repositorio.listarEventosPorDepartamento(departamento);
    }
}
