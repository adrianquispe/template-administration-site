package com.proyecto.persistencia.repositorios;


import com.proyecto.model.espaciosColaborativos.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReservaRepositorio extends JpaRepository<Reserva, UUID> {
  
}
