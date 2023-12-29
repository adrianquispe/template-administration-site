package com.proyecto.persistencia.repositorios;

import com.proyecto.model.departamento.Departamento;
import com.proyecto.model.departamento.TipoDepartamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartamentoRepositorio extends JpaRepository<Departamento, Long> {

    @Query("SELECT d FROM Departamento d WHERE d.departamentoPadre IS NULL")
    public List<Departamento> departamentosSinAsignar();

    @Query("SELECT d FROM Departamento d WHERE d.departamentoPadre IS NULL AND d.tipo = :tipo")
    List<Departamento> departamentosSinAsignarYDeTipo(@Param("tipo") TipoDepartamento tipo);

    @Query("SELECT d FROM Departamento d WHERE d.superDepartamento = true")
    List<Departamento> superDepartamentos();
}

