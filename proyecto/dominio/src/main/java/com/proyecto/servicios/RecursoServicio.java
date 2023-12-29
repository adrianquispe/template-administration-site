package com.proyecto.servicios;

import com.proyecto.model.espaciosColaborativos.Recurso;
import java.util.List;

public interface RecursoServicio {

    public List<Recurso> listarRecurso();

    public Recurso guardarRecurso(Recurso recurso);

    public  Recurso obtenerRecursoPorId(Long id);

    public  Recurso actualizarRecurso(Recurso recurso);

    public void cancelarRecurso(Long id);
}
