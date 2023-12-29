package com.proyecto.controller;


import com.proyecto.model.espaciosColaborativos.*;
import com.proyecto.servicios.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class ReservaController {

    @Autowired
    private ReservaServicioImpl servicioReserva;

    @Autowired
    private DepartamentoServicioImpl servicioDepartamentos;

    @Autowired
    private RecursoServicioImpl recursoServicio;

    @Autowired
    private EspacioColaborativoServicioImpl espacioColaborativoServicio;

    @GetMapping("/reservas")
    public String listarReserva (Model modelo){
        modelo.addAttribute("reservas", servicioReserva.listarReserva());
        return "Espacios_colaborativos/reservas";
    }

    @GetMapping("/nuevaReserva")
    public String mostrarFormularioRegistroReserva(Model modelo){
        Reserva reserva = new Reserva();

        for(Reserva res: servicioReserva.listarReserva()){
            System.out.println(res.getUuid());
        }

        List<String> valoresEstado = new ArrayList<>();
        valoresEstado.add(ValorEstado.PENDIENTE_DE_CONFIRMACION.toString());
        valoresEstado.add(ValorEstado.CONFIRMADO.toString());
        valoresEstado.add(ValorEstado.RECHAZADO.toString());
        valoresEstado.add(ValorEstado.CONFIRMADO_PARCIALMENTE.toString());

        for(String mod : valoresEstado){
            System.out.println(mod);
        }

        for(int i = 0; i < 10; i++){
            RecursoDeReserva rec = new RecursoDeReserva();
            reserva.agregarRecursoDeReserva(rec);
        }


        modelo.addAttribute("reserva", reserva);
        modelo.addAttribute("departamentos", servicioDepartamentos.listaDepartamentos());
        modelo.addAttribute("recursos", recursoServicio.listarRecurso());
        modelo.addAttribute("espacios", espacioColaborativoServicio.listarEspacioColaborativo());
        return "Espacios_colaborativos/crear_reserva";
    }

    @PostMapping("/reserva")
    public String guardarReserva(Model modelo,
                                 @ModelAttribute("reserva") @Valid Reserva reserva,
                                 BindingResult bindingResultReserva,
                                 @RequestParam("fecha") String  fechaString
                                 ){
        if (bindingResultReserva.hasErrors()){
            System.out.println(bindingResultReserva.getAllErrors());

            modelo.addAttribute("departamentos", servicioDepartamentos.listaDepartamentos());
            modelo.addAttribute("recursos", recursoServicio.listarRecurso());
            modelo.addAttribute("espacios", espacioColaborativoServicio.listarEspacioColaborativo());

            return "Espacios_colaborativos/crear_reserva";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fecha = LocalDate.parse(fechaString, formatter);

        System.out.println("la fecha fue " + fecha);

        reserva.setFechaSolicitada(fecha);

        List<RecursoDeReserva> recursosCompletos = new ArrayList<>();

        for(RecursoDeReserva recurso : reserva.getRecursosDeReserva()){
            if(recurso.getRecurso() != null && recurso.getCantidad() != null){
                recurso.setReserva(reserva);
                recursosCompletos.add(recurso);
            }
        }

        reserva.setRecursosDeReserva(recursosCompletos);

        servicioReserva.guardarReserva(reserva);
        return "redirect:reservaCreada/" + reserva.getUuid();

    }

    @GetMapping("/consultarReserva")
    public String consultaReserva(Model modelo,
                                  @RequestParam(name = "codigoReserva", required = false) UUID uuid
                                  /*BindingResult bindingResult*/){
        /*if(bindingResult.hasErrors()){

            Reserva reserva = servicioReserva.obtenerReservaPorId(uuid);
            modelo.addAttribute("reserva", reserva);

            return "Espacios_colaborativos/seguimiento_reserva";
        }*/

        if(uuid != null){
            Reserva reserva = servicioReserva.obtenerReservaPorId(uuid);

            if(reserva == null){
                modelo.addAttribute("solicitud", "No existe su reserva");
                return "Espacios_colaborativos/seguimiento_reserva";
            }else{
                modelo.addAttribute("reserva", reserva);
                return "Espacios_colaborativos/seguimiento_reserva";
            }
        }

        return "Espacios_colaborativos/seguimiento_reserva";
    }


    @PostMapping("/reserva/{uuid}")
    public String cambiarEstadoReserva(Model modelo,
                                      @PathVariable UUID uuid,
                                      @RequestParam(name = "estado") ValorEstado nuevoEstado,
                                       @ModelAttribute("reserva") Reserva reservaActualizada,
                                       BindingResult bindingResult,
                                       @RequestParam(name = "motivo", required = false) String motivo){

        if(nuevoEstado != null){
            EstadoReserva estadoHistorico = new EstadoReserva(nuevoEstado);
            estadoHistorico.setMotivo(motivo);

            Reserva reserva = servicioReserva.obtenerReservaPorId(uuid);
            reserva.setEstado(nuevoEstado);
            reserva.agregarEstado(estadoHistorico);

            List<RecursoDeReserva> recursos = reserva.getRecursosDeReserva();
            List<RecursoDeReserva> recursosNuevos = reservaActualizada.getRecursosDeReserva();

            for(int i = 0; i < recursos.size(); i++){
                recursos.get(i).setCantidad(recursosNuevos.get(i).getCantidad());
            }

            servicioReserva.actualizarReserva(reserva);
        }

        return "redirect:../reservas";
    }

    @GetMapping("/reserva/{uuid}")
    public String mostrarReserva(Model modelo, @PathVariable UUID uuid) {
        Reserva reserva = servicioReserva.obtenerReservaPorId(uuid);
        modelo.addAttribute("reserva", reserva);

        List<String> estadosReserva = new ArrayList<>();
        estadosReserva.add(ValorEstado.PENDIENTE_DE_CONFIRMACION.toString());
        estadosReserva.add(ValorEstado.CONFIRMADO.toString());
        estadosReserva.add(ValorEstado.RECHAZADO.toString());
        estadosReserva.add(ValorEstado.CONFIRMADO_PARCIALMENTE.toString());
        modelo.addAttribute("estados", estadosReserva);

        modelo.addAttribute("recursos", reserva.getRecursosDeReserva());

        return "Espacios_colaborativos/administracion_reserva";
    }


    @GetMapping("/reservaCreada/{uuid}")
    public String reservaCreadaCorrectamente(Model modelo, @PathVariable UUID uuid){
        Reserva reserva = servicioReserva.obtenerReservaPorId(uuid);

        modelo.addAttribute("reserva", reserva);

        return "Espacios_colaborativos/confirmacion_reserva";
    }
}