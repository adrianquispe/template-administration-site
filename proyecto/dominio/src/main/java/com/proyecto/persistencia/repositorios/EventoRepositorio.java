package com.proyecto.persistencia.repositorios;

import com.proyecto.model.departamento.Departamento;
import com.proyecto.model.eventos.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventoRepositorio extends JpaRepository<Evento, UUID> {

    @Query("SELECT e FROM Evento e WHERE e.departamentoOrganizador = :departamento")
    List<Evento> listarEventosPorDepartamento(@Param("departamento") Departamento departamento);
}
