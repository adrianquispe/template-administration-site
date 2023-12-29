package com.proyecto.controller;

import com.proyecto.model.departamento.Departamento;
import com.proyecto.model.departamento.TipoDepartamento;
import com.proyecto.model.departamento.autoridadDepartamento.AutoridadDepartamento;
import com.proyecto.persistencia.repositorios.UsuariosRepositorio;
import com.proyecto.servicios.AutoridadDepartamentoServicioImpl;
import com.proyecto.servicios.DepartamentoServicioImpl;
import com.proyecto.servicios.RolAutoridadServicioImpl;
import com.proyecto.servicios.TipoDepartamentoServicioImpl;
import com.proyecto.valueObjects.FormCrearDepartamento;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class DepartamentoController {

    @Autowired
    private DepartamentoServicioImpl servicioDepartamento;

    @Autowired
    private TipoDepartamentoServicioImpl servicioTipos;

    @Autowired
    private RolAutoridadServicioImpl servicioRoles;

    @Autowired
    private AutoridadDepartamentoServicioImpl servicioAutoridades;

    @Autowired
    private UsuariosRepositorio repositorioUsuarios;

    private static String icono_dir = "logos/iconos";

    @GetMapping("/departamentos")
    public String listarDepartamento (Model modelo, @RequestParam(name = "success", required = false, defaultValue = "false") Boolean success){

        modelo.addAttribute("tipos", servicioTipos.listarTipos());

        if (success) {
            modelo.addAttribute("successMessage", "Departamento creado correctamente!");
        }

        return "departamentos";
    }

    @GetMapping("/departamentos/nuevo")
    public String mostrarRegistroDepartamento (Model modelo,
                                               @RequestParam(defaultValue = "1") Integer cantidad_autoridades){
        Departamento departamento = new Departamento();

        List<AutoridadDepartamento> autoridades = new ArrayList<>();
        for(int i = 0; i<cantidad_autoridades; i++){
            AutoridadDepartamento autoridad = new AutoridadDepartamento();
            autoridades.add(autoridad);
        }

        FormCrearDepartamento formCrearDepartamento = new FormCrearDepartamento();
        formCrearDepartamento.setDepartamento(departamento);
        formCrearDepartamento.setAutoridades(autoridades);

        modelo.addAttribute("formDepartamento", formCrearDepartamento);

        modelo.addAttribute("tipos", servicioTipos.obtenerTipoPorEstado(true));
        modelo.addAttribute("roles", servicioRoles.listarRoles());
        modelo.addAttribute("usuarios",repositorioUsuarios.findAll());

        List<Departamento> departamentosSinAsignar = servicioDepartamento.departamentosSinAsignar();
        if(departamentosSinAsignar == null){
            departamentosSinAsignar = new ArrayList<>();
        }
        modelo.addAttribute("listaDepartamentos", departamentosSinAsignar);
        modelo.addAttribute("cantidad_autoridades", cantidad_autoridades);

        return "crear_departamento";
    }

    @GetMapping("/departamentos/{id}/agregarDepartamentos")
    public String agregarDepartamentos(Model modelo, @PathVariable("id") Long id){
        Departamento departamento = servicioDepartamento.obtenerDepartamentoPorId(id);
        modelo.addAttribute("listaDepartamentos", servicioDepartamento.departamentosSinAsignarYDeTipo(departamento.getTipo()));
        return "agregar_departamentos";
    }

    @PostMapping("/departamentos/{id}/agregarDepartamentos")
    public String agregarDepartamentos(Model modelo,
                                       @PathVariable("id") Long id,
                                       @RequestParam("departamentos") List<Long> departamentos){
        Departamento departamento = servicioDepartamento.obtenerDepartamentoPorId(id);

        for(Long depto: departamentos){
            Departamento departamentolistado = servicioDepartamento.obtenerDepartamentoPorId(depto);
            departamentolistado.setDepartamentoPadre(departamento);
            departamento.agregarDepartamento(departamentolistado);
        }
        departamento.setSuperDepartamento(true);

        servicioDepartamento.actualizarDepartamento(departamento);
        return "redirect:../../departamentos";
    }

    @PostMapping("/departamentos")
    public String guardarDepartamento (Model modelo,
                                       /*@ModelAttribute("departamento") @Valid Departamento departamento, BindingResult bindingResultDepartamento,*/
                                       @ModelAttribute("departamentos") ArrayList<Long> departamentos,
                                       @Valid @ModelAttribute("formDepartamento") FormCrearDepartamento formCrearDepartamento, BindingResult bindingResultForm,
                                       /*@ModelAttribute("autoridadDepartamento") AutoridadDepartamento autoridadDepartamento,*/
                                       @RequestParam("archivoIcono") MultipartFile file) throws IOException {
        System.out.println(modelo.getAttribute("cantidad_autoridades"));

        modelo.addAttribute("cantidad_autoridades",1);

        if (bindingResultForm.hasErrors()){
            System.out.println(bindingResultForm.getAllErrors());
            modelo.addAttribute("tipos", servicioTipos.obtenerTipoPorEstado(true));
            modelo.addAttribute("roles", servicioRoles.listarRoles());
            modelo.addAttribute("usuarios",repositorioUsuarios.findAll());
            List<Departamento> departamentosSinAsignar = servicioDepartamento.departamentosSinAsignar();
            if(departamentosSinAsignar == null){
                departamentosSinAsignar = new ArrayList<>();
            }
            modelo.addAttribute("listaDepartamentos", departamentosSinAsignar);
            return "crear_departamento";
        }
        Departamento departamento = formCrearDepartamento.getDepartamento();

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path uploadPath = Paths.get(icono_dir);
        Files.createDirectories(uploadPath);

        String rutaCompleta = uploadPath.resolve(fileName).toString();
        departamento.setIcono(rutaCompleta);

        try {
            Files.copy(file.getInputStream(), uploadPath.resolve(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(!departamentos.isEmpty()) {
            for (Long depto : departamentos) {
                Departamento departamentolistado = servicioDepartamento.obtenerDepartamentoPorId(depto);
                departamentolistado.setDepartamentoPadre(departamento);
                departamento.agregarDepartamento(departamentolistado);
                departamento.setSuperDepartamento(true);
                //servicioDepartamento.actualizarDepartamento(departamentolistado);
            }
        }

        List<AutoridadDepartamento> autoridades = formCrearDepartamento.getAutoridades();
        departamento.agregarAutoridades(autoridades.toArray(new AutoridadDepartamento[0]));

        servicioDepartamento.actualizarDepartamento(departamento);
        return "redirect:/departamentos?success=true";

    }

    @PostMapping("/departamento/estado/{id}")
    public String darDepartamentoDeBaja (@PathVariable Long id, @RequestParam("estado") Boolean estado){
        Departamento departamento = servicioDepartamento.obtenerDepartamentoPorId(id);
        if(estado){
            departamento.setActivo(true);
        }else{
            departamento.setActivo(false);
        }
        servicioDepartamento.actualizarDepartamento(departamento);
        return "redirect:../../departamentos";
    }

    @GetMapping("/departamento/{departamentoId}/editar")
    public String departamentoEdit(Model model, @PathVariable("departamentoId") Long departamentoId,
                                   @RequestParam(defaultValue = "true") Boolean not_editarNombre,
                                   @RequestParam(defaultValue = "true") Boolean not_editarObjetivo) {
        Optional<Departamento> optionalDepartamento = Optional.ofNullable(servicioDepartamento.obtenerDepartamentoPorId(departamentoId));
        Departamento departamento = optionalDepartamento.get();

        model.addAttribute("departamento", departamento);
        model.addAttribute("tipos", servicioTipos.obtenerTipoPorEstado(true));
        return "editar_departamentos";
    }

    @GetMapping("/departamento/{id}")
    public String infoDepartamento(@PathVariable("id") Long id, Model modelo){
        Departamento departamento = servicioDepartamento.obtenerDepartamentoPorId(id);
        modelo.addAttribute("departamento", departamento);
        modelo.addAttribute("autoridades", departamento.getAutoridades());
        return "infoDepartamento";
    }

    @PostMapping("/departamento/{id}")
    public String editarDepartamento (@PathVariable("id") Long id,
                                      @ModelAttribute @Valid Departamento departamentoActualizado, BindingResult bindingResultDepartamento/*,
                                      @RequestParam("archivoIcono") MultipartFile file*/) /*throws IOException */{
        if(bindingResultDepartamento.hasErrors()){
            return "infoDepartamento";
        }
        /*
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path uploadPath = Paths.get(icono_dir);
        Files.createDirectories(uploadPath);

        String rutaCompleta = uploadPath.resolve(fileName).toString();
        departamentoActualizado.setIcono(rutaCompleta);

        try {
            Files.copy(file.getInputStream(), uploadPath.resolve(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        Departamento departamento = servicioDepartamento.obtenerDepartamentoPorId(id);
        departamento.setNombre(departamentoActualizado.getNombre());
        departamento.setTipo(departamentoActualizado.getTipo());
        departamento.setObjetivo(departamentoActualizado.getObjetivo());
        servicioDepartamento.actualizarDepartamento(departamento);
        return "redirect:../departamentos";
    }



}
