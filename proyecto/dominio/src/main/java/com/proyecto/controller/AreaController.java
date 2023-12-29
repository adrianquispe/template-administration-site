package com.proyecto.controller;

import com.proyecto.model.Area.Area;
import com.proyecto.servicios.AreaServicioImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AreaController {

    @Autowired
    AreaServicioImpl servicio;

    @GetMapping("/areas")
    public String listarAreas(Model modelo){
        modelo.addAttribute("areas", servicio.listarAreas());
        return "areas";
    }

    @GetMapping("/areas/nueva")
    public String mostrarFormularioRegistroArea(Model modelo){
        Area area = new Area();
        modelo.addAttribute("area", area);
        return "crear_area";
    }

    @PostMapping("areas")
    public String guardarArea(Model modelo,
                              @ModelAttribute("area") @Valid Area area,
                              BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "crear_area";
        }
        servicio.guardarArea(area);
        return "redirect:areas";
    }
}
