package com.proyecto.model.departamento;

import com.proyecto.model.departamento.autoridadDepartamento.AutoridadDepartamento;
import com.proyecto.model.Area.Area;
import com.proyecto.model.eventos.Evento;
import com.proyecto.persistencia.Persistencia;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "departamento")
public class Departamento extends Persistencia {

    @Getter
    @Setter
    @Column(name = "nombre")
    @NotBlank(message = "Campo Obligatorio")
    private String nombre;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "tipo_id", referencedColumnName = "id")
    private TipoDepartamento tipo;

    @Getter
    @OneToMany(mappedBy = "departamentoPadre", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    //@JoinColumn(name = "departamento_padre",referencedColumnName = "id")
    private List<Departamento> departamentos;

    @ManyToOne
    @JoinColumn(name = "departamento_padre")
    @Getter @Setter
    private Departamento departamentoPadre;

    @Getter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<AutoridadDepartamento> autoridades;

    @Getter
    @Setter
    @Column(name = "icono")
    private String icono;

    @Getter
    @Setter
    @Column(name = "objetivo")
    @NotBlank(message = "Campo Obligatorio")
    private String objetivo;

    @Getter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Area> areas;

    @Getter @Setter
    @Column(name = "superdepartamento", columnDefinition = "bit")
    private Boolean superDepartamento;

    @Getter
    @Setter
    @Column (name = "activo", columnDefinition = "bit")
    private Boolean activo;

    @Getter
    @OneToMany(mappedBy = "departamentoOrganizador")
    private List<Evento> eventos;


    public Departamento(){
        this.activo = true;
        this.departamentos = new ArrayList<>();
        this.autoridades = new ArrayList<>();
        this.areas = new ArrayList<>();
        this.superDepartamento = false;
        this.eventos = new ArrayList<>();
    }

    public void agregarDepartamento(Departamento... departamentos){
        Collections.addAll(this.departamentos,departamentos);
    }

    public void agregarAreas(Area... areas){
        Collections.addAll(this.areas, areas);
    }

    public void agregarAutoridades(AutoridadDepartamento... autoridades){
        Collections.addAll(this.autoridades, autoridades);
    }

    public void agregarEvento(Evento evento){
        this.eventos.add(evento);
        evento.setDepartamentoOrganizador(this);
    }
}