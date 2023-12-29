package com.proyecto.servicios;

import com.proyecto.model.eventos.ParticipanteEvento;
import com.proyecto.persistencia.repositorios.ParticipanteRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ParticipanteEventoServicio {

    public List<ParticipanteEvento> listarParticipantes();

    public ParticipanteEvento obtenerParticipantePorId(Long id);

    public ParticipanteEvento guardarParticipante(ParticipanteEvento participanteEvento);

    public ParticipanteEvento actualizarParticipante(ParticipanteEvento participanteEvento);
}
