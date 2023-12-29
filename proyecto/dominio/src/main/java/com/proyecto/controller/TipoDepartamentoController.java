package com.proyecto.controller;

import com.proyecto.model.departamento.Departamento;
import com.proyecto.model.departamento.TipoDepartamento;
import com.proyecto.servicios.TipoDepartamentoServicioImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class TipoDepartamentoController {

    @Autowired
    private TipoDepartamentoServicioImpl servicioTipoDepartamento;

    @GetMapping("/tiposDepartamentos")
    public String listarTiposDeDepartamento(Model modelo){
        modelo.addAttribute("tipos", servicioTipoDepartamento.listarTipos());
        return "TiposDepartamentos/tiposDepartamentos";
    }

    @GetMapping("/tipoDepartamento/editar/{id}")
    public String editarTipoDepartamento(Model modelo, @PathVariable Long id){
        TipoDepartamento tipoDepartamento = servicioTipoDepartamento.obtenerTipoPorID(id);

        modelo.addAttribute("tipoDepartamento", tipoDepartamento);

        return "TiposDepartamentos/tipoDepartamento";
    }

    @PostMapping("/tipoDepartamento/estado/{id}")
    public String cambiarEstado(Model modelo,
                                @PathVariable("id") Long id,
                                @RequestParam("estado") Boolean estado){
        TipoDepartamento tipoDepartamento = servicioTipoDepartamento.obtenerTipoPorID(id);
        if(estado){
            tipoDepartamento.setActivo(true);
        }else{
            tipoDepartamento.setActivo(false);
        }

        servicioTipoDepartamento.actualizarTipo(tipoDepartamento);
        return "redirect:/tiposDepartamentos";
    }

    @PostMapping("/tipoDepartamento/{id}")
    public String editarTipo(Model modelo,
                             @PathVariable("id") Long id,
                             @ModelAttribute("tipo") TipoDepartamento tipoDepartamentoActualizado,
                             BindingResult bindingResult
                             ) {
        if(bindingResult.hasErrors()){
            return "tipoDepartamento";
        }
        TipoDepartamento tipoDepartamento = servicioTipoDepartamento.obtenerTipoPorID(id);

        tipoDepartamento.setNombre(tipoDepartamentoActualizado.getNombre());
        servicioTipoDepartamento.actualizarTipo(tipoDepartamento);

        return "redirect:/tiposDepartamentos";
    }


    @GetMapping("/tiposDepartamentos/nuevo")
    public String mostrarFormularioRegistroTipo(Model modelo){
        TipoDepartamento tipoDepartamento = new TipoDepartamento();
        modelo.addAttribute("tipoDepartamento", tipoDepartamento);
        return "TiposDepartamentos/crear_tipo";
    }

    @PostMapping("/tipoDepartamento")
    public String guardarTipoDepartamento(Model modelo,
                                          @ModelAttribute("tipoDepartamento") @Valid TipoDepartamento tipoDepartamento,
                                          BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "TiposDepartamentos/crear_tipo";
        }

        servicioTipoDepartamento.guardarTipo(tipoDepartamento);

        return "redirect:/tiposDepartamentos";
    }
}
