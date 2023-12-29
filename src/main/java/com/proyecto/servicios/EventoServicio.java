package com.proyecto.servicios;

import com.proyecto.model.eventos.Evento;

import java.util.List;

public interface EventoServicio {

    public List<Evento> listarEventos();

    public Evento guardarEvento(Evento evento);

    public  Evento obtenerEventoPorId(long id);

    public  Evento actualizarEvento(Evento evento);

    public void cancelarEvento(Long id);


}
