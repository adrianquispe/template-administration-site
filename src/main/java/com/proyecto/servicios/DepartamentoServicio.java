package com.proyecto.servicios;

import com.proyecto.model.departamento.Departamento;
import com.proyecto.model.departamento.TipoDepartamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartamentoServicio {

    public List<Departamento> listaDepartamentos();

    public Departamento guardarDepartamento(Departamento departamento);

    public Departamento obtenerDepartamentoPorId(long id);

    public Departamento actualizarDepartamento(Departamento departamento);

    public void eliminarDepartamento(Long id);

    public List<Departamento> departamentosSegunTipo(TipoDepartamento tipo);

    public List<Departamento> departamentosSinAsignar();

    List<Departamento> departamentosSinAsignarYDeTipo(@Param("tipo") TipoDepartamento tipo);

    List<Departamento> superDepartamentos();

    Page<Departamento> listaDepartamentos(Pageable pagina);

}

