package com.proyecto.servicios;

import com.proyecto.model.espaciosColaborativos.EstadoReserva;

import java.util.List;
import java.util.UUID;

public interface EstadoReservaServicio {

    public List<EstadoReserva> listarEstadoReserva();

    public EstadoReserva guardarEstadoReserva(EstadoReserva estadoReserva);

    public  EstadoReserva obtenerEstadoReservaPorId(Long id);

    public  EstadoReserva actualizarEstadoReserva(EstadoReserva estadoReserva);

    public void cancelarEstadoReserva(Long id);
}
