package com.proyecto.controller;


import com.proyecto.model.eventos.*;
import com.proyecto.model.socio.ubicacion.Ubicacion;
import com.proyecto.servicios.*;
import jakarta.validation.Valid;
import jdk.jfr.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
public class EventoController {

    @Autowired
    private EventoServicioImpl servicioEventos;

    @Autowired
    private DepartamentoServicioImpl servicioDepartamentos;

    @Autowired
    private ParticipanteEventoServicioImpl servicioParticipantes;

    @Autowired
    private SocioSocioServicioImpl servicioSocios;

    @Autowired
    private UbicacionServicioImpl servicioUbicacion;

    private static String icono_dir = "logos/iconos";

    @GetMapping("/eventos")
    public String listarEventos(Model modelo){
        modelo.addAttribute("eventos", servicioEventos.listarEventos());
        for(Evento ev : servicioEventos.listarEventos()){
            System.out.println(ev.getId());
            System.out.println(ev.getImagen());
        }
        modelo.addAttribute("departamentos", servicioDepartamentos.listaDepartamentos());

        List<String> modalidades = new ArrayList<>();
        modalidades.add(TipoEvento.PRESENCIAL.toString());
        modalidades.add(TipoEvento.HIBRIDO.toString());
        modalidades.add(TipoEvento.VIRTUAL.toString());
        modelo.addAttribute("modalidades", modalidades);

        List<String> estados = new ArrayList<>();
        estados.add(ValorEstado.ACTIVO.toString());
        estados.add(ValorEstado.FINALIZADO.toString());
        estados.add(ValorEstado.CANCELADO.toString());
        modelo.addAttribute("estados", estados);

        return "Eventos/eventos";
    }


    @GetMapping("/nuevoEvento")
    public String mostrarFormularioRegistroEvento(Model modelo){
        Evento evento = new Evento();
        Ubicacion ubicacion = new Ubicacion();
        evento.setUbicacion(ubicacion);

        List<String> modalidades = new ArrayList<>();
        modalidades.add(TipoEvento.PRESENCIAL.toString());
        modalidades.add(TipoEvento.VIRTUAL.toString());
        modalidades.add(TipoEvento.HIBRIDO.toString());

        for(String mod : modalidades){
            System.out.println(mod);
        }

        modelo.addAttribute("evento", evento);
        modelo.addAttribute("departamentos", servicioDepartamentos.listaDepartamentos());
        modelo.addAttribute("modalidades", modalidades);
        return "Eventos/crear_evento";
    }

    @PostMapping("/eventos")
    public String guardarEvento(Model modelo,
                              @ModelAttribute("evento") @Valid Evento evento,
                              BindingResult bindingResult,
                                @RequestParam("archivoLogo") MultipartFile file) throws IOException {
        if(bindingResult.hasErrors()){
            List<String> modalidades = new ArrayList<>();
            modalidades.add(TipoEvento.PRESENCIAL.toString());
            modalidades.add(TipoEvento.VIRTUAL.toString());
            modalidades.add(TipoEvento.HIBRIDO.toString());
            modelo.addAttribute("modalidades", modalidades);
            modelo.addAttribute("departamentos", servicioDepartamentos.listaDepartamentos());
            return "Eventos/crear_evento";
        }

        if(evento.getTipo() == TipoEvento.VIRTUAL){
            Optional<Ubicacion> ubicacion = Optional.ofNullable(servicioUbicacion.obtenerUbicacionVirtual());
            if(ubicacion.isPresent()){
                evento.setUbicacion(ubicacion.get());
            }else {
                Ubicacion ubicacionEvento = new Ubicacion();
                ubicacionEvento.setCalle("virtual");
                ubicacionEvento.setPiso("0");
                ubicacionEvento.setProvincia("virtual");
                ubicacionEvento.setAltura("0");
                ubicacionEvento.setDepartamento("virtual");
                ubicacionEvento.setBarrio("virtual");
                ubicacionEvento.setMunicipio("virtual");
                servicioUbicacion.guardarUbicacion(ubicacionEvento);

                evento.setUbicacion(ubicacionEvento);
            }
        }else {
            System.out.println("entre");
            Ubicacion eventoUbi = evento.getUbicacion();
            System.out.println(eventoUbi.getCalle());
            if(eventoUbi.getPiso().trim().isEmpty()){
                System.out.println("entre 1");
                eventoUbi.setPiso(null);
            }
            if(eventoUbi.getProvincia().trim().isEmpty()){
                System.out.println("entre 2");
                eventoUbi.setProvincia(null);
            }
            if(eventoUbi.getCalle().trim().isEmpty()){
                System.out.println("entre 3");
                eventoUbi.setCalle(null);
            }
            if(eventoUbi.getAltura().trim().isEmpty()){
                eventoUbi.setAltura(null);
            }
            servicioUbicacion.guardarUbicacion(evento.getUbicacion());
        }


        if(!file.isEmpty()) {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            Path uploadPath = Paths.get(icono_dir);
            Files.createDirectories(uploadPath);

            String rutaCompleta = uploadPath.resolve(fileName).toString();
            evento.setImagen(rutaCompleta);

            try {
                Files.copy(file.getInputStream(), uploadPath.resolve(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        servicioEventos.guardarEvento(evento);
        evento.setLink(evento.linkFormulario());
        servicioEventos.actualizarEvento(evento);

        return "redirect:eventos";
    }

    @GetMapping("/inscripcion/{uuid}")
    public String formulario(Model modelo, @PathVariable UUID uuid){

        Evento evento = servicioEventos.obtenerEventoPorId(uuid);
        modelo.addAttribute("evento", evento);

        ParticipanteEvento participanteEvento = new ParticipanteEvento();
        modelo.addAttribute("participanteEvento", participanteEvento);

        modelo.addAttribute("socios", servicioSocios.listaSocios());

        return "Eventos/form_inscripcion";
    }

    @PostMapping("/inscripcion/{uuid}")
    public String inscripcion(Model modelo,
                              @PathVariable UUID uuid,
                              @ModelAttribute("participante") @Valid ParticipanteEvento participante,
                              BindingResult bindingResult){
        if(bindingResult.hasErrors()){

            Evento evento = servicioEventos.obtenerEventoPorId(uuid);
            modelo.addAttribute("evento", evento);

            ParticipanteEvento participanteEvento = new ParticipanteEvento();
            modelo.addAttribute("participanteEvento", participanteEvento);

            modelo.addAttribute("socios", servicioSocios.listaSocios());
            return "Eventos/form_inscripcion";
        }
        Evento evento = servicioEventos.obtenerEventoPorId(uuid);

        servicioParticipantes.guardarParticipante(participante);

        evento.agregarParticipante(participante);
        servicioEventos.actualizarEvento(evento);

        ParticipanteEvento participanteEvento = new ParticipanteEvento();
        modelo.addAttribute("participanteEvento", participanteEvento);
        modelo.addAttribute("socios", servicioSocios.listaSocios());

        modelo.addAttribute("evento", evento);

        return "Eventos/form_inscripcion";
    }


    @PostMapping("/evento/{uuid}")
    public String cambiarEstadoEvento(Model modelo,
                                      @PathVariable UUID uuid,
                                      @RequestParam(name = "estado")ValorEstado nuevoEstado){

        if(nuevoEstado != null){
            EstadoEvento estadoHistorico = new EstadoEvento(nuevoEstado);

            Evento evento = servicioEventos.obtenerEventoPorId(uuid);
            evento.setEstado(nuevoEstado);
            evento.agregarEstado(estadoHistorico);

            servicioEventos.actualizarEvento(evento);
        }


        return "redirect:../eventos";
    }

    @GetMapping("/evento/{uuid}")
    public String mostrarEvento(Model modelo, @PathVariable UUID uuid) {
        Evento evento = servicioEventos.obtenerEventoPorId(uuid);
        modelo.addAttribute("evento", evento);
        List<String> estados = new ArrayList<>();
        estados.add(ValorEstado.ACTIVO.toString());
        estados.add(ValorEstado.CANCELADO.toString());
        estados.add(ValorEstado.FINALIZADO.toString());
        modelo.addAttribute("estados", estados);
        return "Eventos/infoEvento";
    }

}
