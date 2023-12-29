package com.proyecto.controller;

import com.proyecto.model.espaciosColaborativos.EspacioColaborativo;
import com.proyecto.model.espaciosColaborativos.Recurso;
import com.proyecto.servicios.EspacioColaborativoServicioImpl;
import com.proyecto.servicios.RecursoServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecursosControllerREST {

  @Autowired
  private RecursoServicioImpl recursoServicio;

  @Autowired
  private EspacioColaborativoServicioImpl espacioColaborativoServicio;

  @PostMapping("/api/recurso")
  public String crearRecurso(@RequestBody Recurso recurso) {
    recursoServicio.guardarRecurso(recurso);

    return "Recurso de nombre: " + recurso.getNombre() + " creado correctamente";
  }

  @PostMapping("/api/espacioColaborativo")
  public String crearEspacioColaborativo(@RequestBody EspacioColaborativo espacioColaborativo){
    espacioColaborativoServicio.guardarEspacioColaborativo(espacioColaborativo);

    return "Espacio colaborativo creado correctamente";
  }
}
