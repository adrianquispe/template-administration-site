package com.proyecto.servicios;

import com.proyecto.model.departamento.autoridadDepartamento.AutoridadDepartamento;

import java.util.List;

public interface AutoridadDepartamentoServicio {

    public List<AutoridadDepartamento> listaAutoridadesDepartamentos();

    public AutoridadDepartamento guardarAutoridadDepartamento(AutoridadDepartamento autoridadDepartamento);

    public AutoridadDepartamento obtenerAutoridadDepartamentoPorId(long id);

    public AutoridadDepartamento actualizarAutoridadDepartamento(AutoridadDepartamento autoridadDepartamento);

    public  void eliminarAutoridadDepartamento(Long id);

}
