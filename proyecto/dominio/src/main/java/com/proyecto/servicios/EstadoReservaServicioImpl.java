package com.proyecto.servicios;

import com.proyecto.model.espaciosColaborativos.EstadoReserva;
import com.proyecto.persistencia.repositorios.EstadoReservaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EstadoReservaServicioImpl implements EstadoReservaServicio{

    @Autowired
    private EstadoReservaRepositorio repositorio;

    @Override
    public EstadoReserva obtenerEstadoReservaPorId(Long id) {
        return repositorio.findById(id).get();
    }
    @Override
    public List<EstadoReserva> listarEstadoReserva(){
        return repositorio.findAll();
    }

    @Override
    public EstadoReserva guardarEstadoReserva(EstadoReserva estadoReserva) {
        return repositorio.save(estadoReserva);
    }

    @Override
    public EstadoReserva actualizarEstadoReserva(EstadoReserva estadoReserva) {
        return repositorio.save(estadoReserva);
    }

    @Override
    public void cancelarEstadoReserva(Long id) {
        repositorio.deleteById(id);
    }
}
