package com.proyecto.controller;

import com.proyecto.model.departamento.autoridadDepartamento.RolAutoridad;
import com.proyecto.servicios.RolAutoridadServicioImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RolAutoridadController {

  @Autowired
  private RolAutoridadServicioImpl servicioRoles;

  @GetMapping("/rolAutoridad/nuevo")
  public String mostrarRegistroNuevoRol(Model modelo){
    RolAutoridad rolAutoridad = new RolAutoridad();
    modelo.addAttribute("rolAutoridad", rolAutoridad);
    return "crear_rol";
  }

  @PostMapping("/rolAutoridad")
  public String guardarRol(Model modelo,
                         @ModelAttribute("rolAutoridad") @Valid RolAutoridad rolAutoridad,
                         BindingResult bindingResult){
    if(bindingResult.hasErrors()){
      return "redirect:../nuevo";
    }
    servicioRoles.guardarRol(rolAutoridad);
    return "redirect:/autoridadesDepartamentos/nuevo";
  }
}
