package com.proyecto.model.socio;


import com.proyecto.model.socio.membresiaDeSocio.MembresiaDeSocio;
import com.proyecto.persistencia.Persistencia;
import com.proyecto.model.socio.ubicacion.Ubicacion;
import com.proyecto.model.usuario.Usuario;
import com.proyecto.model.Area.Area;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "Socios")
public class Socio extends Persistencia {

    @Getter @Setter
    @Column(name = "denominacion")
    @NotBlank(message = "Campo Obligatorio")
    private String denominacion;

    @Getter @Setter
    @Column(name = "fecha_asociacion")
    private LocalDate fechaDeAsociacion;

    @Getter @Setter
    @Column(name = "descripcion")
    @NotBlank(message = "Campo Obligatorio")
    private String descripcion;

    @Getter @Setter
    @Column(name = "logo")
    //@NotBlank(message = "Campo Obligatorio")
    private String logo;

    @Getter @Setter
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "socio_id")
    private List<MembresiaDeSocio> membresias;

    @Getter @Setter
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "ubicacion_id")
    private Ubicacion ubicacion;

    @Getter @Setter
    @Column(name = "mail")
    @Pattern(regexp = "^[\\w\\.-]+@[\\w\\.-]+\\.\\w+", message = "El formato del correo no es válido")
    @NotNull(message = "Campo Obligatorio")
    private String mail;

    @Getter @Setter
    @Column(name = "telefono")
    @NotBlank(message = "Ingrese el número de teléfono")
    @Pattern(regexp = "^[0-9]+$", message = "El número de teléfono debe contener solo dígitos")
    private String telefono;

    @Getter @Setter
    @Column(name = "pagina")
    @NotBlank(message = "Ingrese la página web")
    private String pagina;

    @Getter @Setter
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Getter @Setter
    @Column(name = "activo", columnDefinition = "bit")
    private Boolean activo;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private TipoSocio tipo;

    @Getter
    @ManyToMany
    private List<Area> areas;

    public Socio(){
        this.activo = true;
        this.fechaDeAsociacion = LocalDate.now();
        this.areas = new ArrayList<>();
        this.membresias = new ArrayList<>();
    }

    public void agregarMembresia(MembresiaDeSocio... membresiaDeSocios){
        Collections.addAll(this.membresias, membresiaDeSocios);
    }

    public Boolean camara(){
        return this.tipo == TipoSocio.CAMARA;
    }

    public void agregarAreas(Area... areas){
        Collections.addAll(this.areas, areas);
    }

}
