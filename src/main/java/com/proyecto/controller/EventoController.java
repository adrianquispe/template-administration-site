package com.proyecto.controller;


import com.proyecto.model.eventos.Evento;
import com.proyecto.servicios.EventoServicioImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventoController {

    @Autowired
    EventoServicioImpl servicio;

    @GetMapping("/eventos")
    public String listarEventos(Model modelo){
        modelo.addAttribute("eventos", servicio.listarEventos());
        return "eventos";
    }


    @GetMapping("/departamento/{id}/nuevoEvento")
    public String mostrarFormularioRegistroEvento(Model modelo){
        Evento evento = new Evento();
        modelo.addAttribute("evento", evento);
        return "crear_evento";
    }

    @PostMapping("/eventos")
    public String guardarEvento(Model modelo,
                              @ModelAttribute("evento") @Valid Evento evento,
                              BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "crear_evento";
        }
        servicio.guardarEvento(evento);
        return "redirect:eventos";
    }

}
