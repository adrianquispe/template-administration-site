package com.proyecto.persistencia.repositorios;

import com.proyecto.model.espaciosColaborativos.EstadoReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoReservaRepositorio extends JpaRepository<EstadoReserva, Long> {
}
