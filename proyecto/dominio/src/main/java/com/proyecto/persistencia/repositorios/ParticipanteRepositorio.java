package com.proyecto.persistencia.repositorios;

import com.proyecto.model.eventos.ParticipanteEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipanteRepositorio extends JpaRepository<ParticipanteEvento, Long> {
}
