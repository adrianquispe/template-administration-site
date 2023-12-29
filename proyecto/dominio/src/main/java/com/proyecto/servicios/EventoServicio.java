package com.proyecto.servicios;

import com.proyecto.model.eventos.Evento;

import java.util.List;
import java.util.UUID;

public interface EventoServicio {

    public List<Evento> listarEventos();

    public Evento guardarEvento(Evento evento);

    public  Evento obtenerEventoPorId(UUID id);

    public  Evento actualizarEvento(Evento evento);

    public void cancelarEvento(UUID id);


}
