package com.proyecto.servicios;


import com.proyecto.model.espaciosColaborativos.Reserva;

import java.util.List;
import java.util.UUID;

public interface ReservaServicio {

    public List<Reserva> listarReserva();

    public Reserva guardarReserva(Reserva reserva);

    public  Reserva obtenerReservaPorId(UUID uuid);

    public  Reserva actualizarReserva(Reserva reserva);

    public void cancelarReserva(UUID uuid);

}
