package com.proyecto.servicios;

import com.proyecto.model.espaciosColaborativos.Reserva;
import com.proyecto.persistencia.repositorios.ReservaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReservaServicioImpl implements ReservaServicio{

    @Autowired
    private ReservaRepositorio repositorio;

    @Override
    public Reserva obtenerReservaPorId(UUID uuid) {
        return repositorio.findById(uuid).get();
    }
    @Override
    public List<Reserva> listarReserva(){
        return repositorio.findAll();
    }

    @Override
    public Reserva guardarReserva(Reserva reserva) {
        return repositorio.save(reserva);
    }

    @Override
    public Reserva actualizarReserva(Reserva reserva) {
        return repositorio.save(reserva);
    }

    @Override
    public void cancelarReserva(UUID uuid) {
        repositorio.deleteById(uuid);
    }
}
