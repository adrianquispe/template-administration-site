package com.proyecto.controller;

import com.proyecto.model.departamento.autoridadDepartamento.AutoridadDepartamento;
import com.proyecto.servicios.AutoridadDepartamentoServicioImpl;
import com.proyecto.servicios.RolAutoridadServicioImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AutoridadDepartamentoController {

    @Autowired
    private AutoridadDepartamentoServicioImpl servicio;

    @Autowired
    private RolAutoridadServicioImpl rolAutoridadServicio;

    @GetMapping("/autoridadesDepartamentos")
    public String listarAutoridadDepartamento(Model modelo){
        modelo.addAttribute("autoridadesDepartamentos", servicio.listaAutoridadesDepartamentos());
        return "autoridadesDepartamentos";
    }

    @GetMapping("/autoridadesDepartamentos/nuevo")
    public  String mostrarRegistrosAutoridadDepartamento (Model modelo){
        AutoridadDepartamento autoridadDepartamento = new AutoridadDepartamento();
        modelo.addAttribute("autoridadDepartamento", autoridadDepartamento);
        modelo.addAttribute("roles", rolAutoridadServicio.rolesSegunEstado(true));
        return "crear_autoridadDepartamento";
    }

    @PostMapping("/autoridadesDepartamentos")
    public String guardarAutoridadDepartamento(Model modelo,
                                               @ModelAttribute("autoridadDepartamento") @Valid AutoridadDepartamento autoridadDepartamento,
                                               BindingResult bindingResultAutoridadDepartamento){
        if (bindingResultAutoridadDepartamento.hasErrors()){
            System.out.println(bindingResultAutoridadDepartamento.getAllErrors());
            return "crear_autoridadDepartamento";
        }

        servicio.guardarAutoridadDepartamento(autoridadDepartamento);
        return "redirect:autoridadesDepartamentos";

    }

    @PostMapping("/autoridadDepartamento/{id}")
    public String darAutoridadDepartamentoDeBaja(@PathVariable Long id, @RequestParam("estado") Boolean estado){
       AutoridadDepartamento autoridadDepartamento = servicio.obtenerAutoridadDepartamentoPorId(id);
       if(estado == false){
           autoridadDepartamento.setActivo(false);
       }else{
           autoridadDepartamento.setActivo(true);
       }
       servicio.actualizarAutoridadDepartamento(autoridadDepartamento);
       return "redirect:../autoridadesDepartamentos";
    }
/*
    @PostMapping("/autoridadDepartamento/reactivar/{id}")
    public String reactivarAutoridadDepartamento(@PathVariable Long id){
        AutoridadDepartamentoController autoridadDepartamento = servicio.obtenerAutoridadDepartamentoPorId(id);
        autoridadDepartamento.setActivo(true);
        servicio.actualizarAutoridadDepartamento(autoridadDepartamento);
        return "redirect:../../autoridadesDepartamentos";//ver si hay conflicto con el orden de carpetas
    }
*/

    @GetMapping("/autoridadDepartamento/{id}/editar")
    public String autoridadEdit(Model modelo, @PathVariable("id") Long id){
        Optional<AutoridadDepartamento> optionalAutoridad = Optional.ofNullable(servicio.obtenerAutoridadDepartamentoPorId(id));
        AutoridadDepartamento autoridadDepartamento = optionalAutoridad.get();


        modelo.addAttribute("autoridadDepartamento", autoridadDepartamento);
        modelo.addAttribute("roles",rolAutoridadServicio.rolesSegunEstado(true));

        return "autoridad";
    }

    @PostMapping("/autoridadDepartamento/editar/{id}")
    public String editarAutoridadDepartamento(@PathVariable Long id,
                                              @ModelAttribute("autoridadDepartamento") @Valid AutoridadDepartamento autoridadDepartamentoActualizado,
                                              BindingResult bindingResultAutoridadDepartamento){
        if(bindingResultAutoridadDepartamento.hasErrors()){
            return "infoAutoridadDepartamento";
        }

        AutoridadDepartamento autoridadDepartamento = servicio.obtenerAutoridadDepartamentoPorId(id);
        autoridadDepartamento.getUsuario().setNombre(autoridadDepartamentoActualizado.getUsuario().getNombre());
        autoridadDepartamento.getUsuario().setApellido(autoridadDepartamentoActualizado.getUsuario().getApellido());
        autoridadDepartamento.setRol(autoridadDepartamentoActualizado.getRol());
        servicio.actualizarAutoridadDepartamento(autoridadDepartamento);
        return "redirect:../../autoridadesDepartamentos";
    }
}
