package com.proyecto.persistencia.repositorios;

import com.proyecto.model.departamento.TipoDepartamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDepartamentoRepositorio extends JpaRepository<TipoDepartamento, Long> {
}
