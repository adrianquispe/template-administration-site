package com.proyecto.controller;

import com.proyecto.model.departamento.Departamento;
import com.proyecto.model.departamento.autoridadDepartamento.AutoridadDepartamento;
import com.proyecto.model.usuario.Usuario;
import com.proyecto.persistencia.repositorios.UsuariosRepositorio;
import com.proyecto.servicios.AutoridadDepartamentoServicioImpl;
import com.proyecto.servicios.DepartamentoServicioImpl;
import com.proyecto.servicios.RolAutoridadServicioImpl;
import com.proyecto.servicios.TipoDepartamentoServicioImpl;
import com.proyecto.valueObjects.FormCrearDepartamento;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControllerPrueba {

  @Autowired
  private DepartamentoServicioImpl servicioDepartamentos;

  @Autowired
  private AutoridadDepartamentoServicioImpl servicioAutoridades;

  @Autowired
  private UsuariosRepositorio usuariosRepositorio;

  @Autowired
  private RolAutoridadServicioImpl rolAutoridadServicio;

  @Autowired
  private TipoDepartamentoServicioImpl tipoDepartamentoServicio;

  @GetMapping("/prueba/{id}")
  public String getPrueba(Model model, @PathVariable("id") Long id,
                          @RequestParam(defaultValue = "1") Integer cantidad_autoridades){
    Departamento departamento = servicioDepartamentos.obtenerDepartamentoPorId(1);
    /*model.addAttribute("departamento", departamento);*/
    model.addAttribute("tipos",tipoDepartamentoServicio.listarTipos());
    model.addAttribute("usuarios", usuariosRepositorio.findAll());
    model.addAttribute("roles", rolAutoridadServicio.listarRoles());

    List<AutoridadDepartamento> autoridades = new ArrayList<>();
    for(int i = 0; i<cantidad_autoridades; i++){
      AutoridadDepartamento autoridad = new AutoridadDepartamento();
      autoridades.add(autoridad);
    }

    FormCrearDepartamento formCrearDepartamento = new FormCrearDepartamento();
    formCrearDepartamento.setDepartamento(departamento);
    formCrearDepartamento.setAutoridades(autoridades);

    model.addAttribute("formDepartamento", formCrearDepartamento);
    /*model.addAttribute("autoridades", autoridades);*/
    model.addAttribute("cantidad_autoridades", cantidad_autoridades);
    return "getprueba";
  }

  @PostMapping("/prueba")
  public String postPrueba(Model modelo,
                           /*@ModelAttribute("departamento") Departamento departamento,
                           @ModelAttribute("autoridades") ArrayList<AutoridadDepartamento> autoridades,*/
                           @ModelAttribute("formDepartamento") FormCrearDepartamento formCrearDepartamento){

    for(AutoridadDepartamento autoridadDepartamento :  formCrearDepartamento.getAutoridades()) {
      System.out.println(autoridadDepartamento.getUsuario().getNombre());
      System.out.println(autoridadDepartamento.getUsuario().getApellido());
      System.out.println(autoridadDepartamento.getRol().getDescripcion());
    }

    System.out.println(formCrearDepartamento.getDepartamento().getNombre());

    if(formCrearDepartamento.getAutoridades().isEmpty()){
      System.out.println("Esta vacia?");
    }

    Departamento departamento = formCrearDepartamento.getDepartamento();
    List<AutoridadDepartamento> autoridades = formCrearDepartamento.getAutoridades();
    departamento.agregarAutoridades(autoridades.toArray(new AutoridadDepartamento[0]));
    //servicioAutoridades.guardarAutoridadDepartamento(autoridadDepartamento);

    //departamento.agregarAutoridades(autoridadDepartamento);

    servicioDepartamentos.actualizarDepartamento(departamento);

    return "redirect:prueba/1";
  }


}
