package com.proyecto.persistencia.repositorios;

import com.proyecto.model.departamento.autoridadDepartamento.AutoridadDepartamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AutoridadDepartamentoRepositorio extends JpaRepository<AutoridadDepartamento, Long> {
}
