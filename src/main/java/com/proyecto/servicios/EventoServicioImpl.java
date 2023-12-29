package com.proyecto.servicios;

import com.proyecto.model.eventos.Evento;
import com.proyecto.persistencia.repositorios.EventoRepositorio;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoServicioImpl implements EventoServicio{

    @Autowired
    EventoRepositorio repositorio;

    @Override
    public Evento obtenerEventoPorId(long id) {
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
    public void cancelarEvento(Long id) {
        repositorio.deleteById(id);
    }

}
