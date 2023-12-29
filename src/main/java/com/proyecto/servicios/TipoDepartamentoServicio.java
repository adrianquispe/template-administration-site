package com.proyecto.servicios;

import com.proyecto.model.departamento.Departamento;
import com.proyecto.model.departamento.TipoDepartamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TipoDepartamentoServicio {

    public List<TipoDepartamento> listarTipos();

    public TipoDepartamento guardarTipo(TipoDepartamento tipo);

    public TipoDepartamento obtenerTipoPorID(Long id);

    public TipoDepartamento obtenerTipoPorNombre(String nombre);

    public TipoDepartamento actualizarTipo(TipoDepartamento tipo);

    Page<TipoDepartamento> listarTipos(Pageable pagina);

}
