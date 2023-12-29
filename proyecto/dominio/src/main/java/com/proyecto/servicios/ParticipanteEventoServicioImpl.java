package com.proyecto.servicios;

import com.proyecto.model.eventos.ParticipanteEvento;
import com.proyecto.persistencia.repositorios.ParticipanteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipanteEventoServicioImpl implements ParticipanteEventoServicio{

    @Autowired
    private ParticipanteRepositorio repositorio;

    @Override
    public List<ParticipanteEvento> listarParticipantes() {
        return repositorio.findAll();
    }

    @Override
    public ParticipanteEvento obtenerParticipantePorId(Long id) {
        return repositorio.findById(id).get();
    }

    @Override
    public ParticipanteEvento guardarParticipante(ParticipanteEvento participanteEvento) {
        return repositorio.save(participanteEvento);
    }

    @Override
    public ParticipanteEvento actualizarParticipante(ParticipanteEvento participanteEvento) {
        return repositorio.save(participanteEvento);
    }
}
