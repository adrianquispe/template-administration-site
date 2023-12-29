package com.proyecto.persistencia.repositorios;

import com.proyecto.model.espaciosColaborativos.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecursoRepositorio extends JpaRepository<Recurso, Long> {
}
