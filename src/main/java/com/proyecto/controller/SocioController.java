package com.proyecto.controller;

import com.proyecto.excepciones.EntityNotFoundException;
import com.proyecto.servicios.SocioSocioServicioImpl;
import com.proyecto.model.socio.Socio;
import com.proyecto.model.socio.ubicacion.Ubicacion;
import com.proyecto.model.usuario.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
import java.util.Objects;
import java.util.Optional;

@Controller
@Component
public class SocioController {

    //@Value("${upload.path}")
    private static String logos_dir = "logos/";

    @Autowired
    private SocioSocioServicioImpl servicio;

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    @GetMapping("/socio/{socioId}")
    public String index(Model model, @PathVariable("socioId") Long socioId) {
        Optional<Socio> optionalSocio = Optional.ofNullable(servicio.obtenerSocioPorId(socioId));
        Socio socio = optionalSocio.get();

        Ubicacion ubicacion = socio.getUbicacion() == null ? new Ubicacion() : socio.getUbicacion();

        model.addAttribute("socio", socio);
        model.addAttribute("ubicacion", ubicacion);

        return "infoSocio";
    }

    @GetMapping("/socio/{socioId}/editar")
    public String socioEdit(Model model, @PathVariable("socioId") Long socioId) {
        Optional<Socio> optionalSocio = Optional.ofNullable(servicio.obtenerSocioPorId(socioId));
        Socio socio = optionalSocio.get();

        Ubicacion ubicacion = socio.getUbicacion() == null ? new Ubicacion() : socio.getUbicacion();

        model.addAttribute("socio", socio);
        model.addAttribute("ubicacion", ubicacion);

        return "socio";
    }

    @PostMapping("/socio/editar/{id}")
    public String editarSocio(@PathVariable Long id,
                              @ModelAttribute @Valid Socio socioActualizado, BindingResult bindingResultSocio,
                              @ModelAttribute @Valid Ubicacion ubicacionActualizada, BindingResult bindingResultUbicacion,
                              @RequestParam("archivoLogo") MultipartFile file) throws IOException {
        if(bindingResultSocio.hasErrors()){
            return "infoSocio";
        }
        if(bindingResultUbicacion.hasErrors()) {
            return "infoSocio";
        }
        Socio socio = servicio.obtenerSocioPorId(id);

        if(!file.isEmpty()) {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            Path uploadPath = Paths.get(logos_dir);
            Files.createDirectories(uploadPath);

            String rutaCompleta = uploadPath.resolve(fileName).toString();
            socioActualizado.setLogo(rutaCompleta);
            socio.setLogo(socioActualizado.getLogo());
            try {
                Files.copy(file.getInputStream(), uploadPath.resolve(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            //socio.setLogo(socioActualizado.getLogo());
        }



        socio.setDenominacion(socioActualizado.getDenominacion());
        socio.setDescripcion(socioActualizado.getDescripcion());
        socio.setPagina(socioActualizado.getPagina());
        socio.setMail(socioActualizado.getMail());
        socio.setTelefono(socioActualizado.getTelefono());
        socio.setUbicacion(ubicacionActualizada);
        socio.setTipo(socioActualizado.getTipo());

        servicio.actualizarSocio(socio);

        return "redirect:../../socios";
    }

    @GetMapping("/socios")
    public String listarSocios(Model modelo) {
        modelo.addAttribute("socios", servicio.listaSocios());
        return "socios";
    }

    @GetMapping("/socios/nuevo")
    public String mostrarFormularioRegistroSocios(Model modelo) {
        Socio socio = new Socio();
        Ubicacion ubicacion = new Ubicacion();
        socio.setUbicacion(ubicacion);
        modelo.addAttribute("socio", socio);
        modelo.addAttribute("ubicacion", ubicacion);

        return "crear_socio";   //Retorna al archivo html (el nombre del archivo)
    }


    @PostMapping("/socios")
    public String guardarSocio(Model model,
                               @ModelAttribute("socio") @Valid Socio socio, BindingResult bindResultSocio,
                               @ModelAttribute("ubicacion") @Valid Ubicacion ubicacion, BindingResult bindResultUbicacion,
                               /*@ModelAttribute("usuario") @Valid Usuario usuario, BindingResult bindResultUsuario,*/
                               @RequestParam("archivoLogo") MultipartFile file) throws IOException {
        if(bindResultSocio.hasErrors()){
            System.out.println(bindResultSocio.getAllErrors());
            return "crear_socio";
        }
        if(bindResultUbicacion.hasErrors()){
            System.out.println(bindResultUbicacion.getAllErrors());
            return "crear_socio";
        }/*
        if(bindResultUsuario.hasErrors()){
            System.out.println(bindResultUsuario.getAllErrors());
            return "crear_socio";
        }*/

        if(!file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path uploadPath = Paths.get(logos_dir);
            Files.createDirectories(uploadPath);

            String rutaCompleta = uploadPath.resolve(fileName).toString();
            socio.setLogo(rutaCompleta);

            try {
                Files.copy(file.getInputStream(), uploadPath.resolve(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        socio.setUbicacion(ubicacion);
        //socio.setUsuario(usuario);
        servicio.guardarSocio(socio);
        
       // Redirigir con query param para mostrar un retorno mas lindo
       return "redirect:socios";
    }

    @PostMapping("/socio/{id}/estado")
    public String cambiarEstadoSocio(Model modelo, @PathVariable("id") Long id, @RequestParam("estado") Boolean estado){
        Socio socio = servicio.obtenerSocioPorId(id);
        if(estado){
            socio.setActivo(true);
        }else{
            socio.setActivo(false);
        }
        servicio.actualizarSocio(socio);
        return "redirect:../../socios";
    }


}