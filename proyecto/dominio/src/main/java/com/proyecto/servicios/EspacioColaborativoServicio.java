package com.proyecto.servicios;

import com.proyecto.model.espaciosColaborativos.EspacioColaborativo;

import java.util.List;

public interface EspacioColaborativoServicio {

    public List<EspacioColaborativo> listarEspacioColaborativo();

    public EspacioColaborativo guardarEspacioColaborativo(EspacioColaborativo espacioColaborativo);

    public  EspacioColaborativo obtenerEspacioColaborativoPorId(Long id);

    public  EspacioColaborativo actualizarEspacioColaborativo(EspacioColaborativo espacioColaborativo);

    public void cancelarEspacioColaborativo(Long id);
}
