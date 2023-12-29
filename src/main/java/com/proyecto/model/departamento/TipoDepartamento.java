package com.proyecto.model.departamento;

import com.proyecto.model.departamento.autoridadDepartamento.AutoridadDepartamento;
import com.proyecto.persistencia.Persistencia;
import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TipoDepartamento")
@Setter @Getter
public class TipoDepartamento extends Persistencia{

    @Column(name = "nombre")
    @NotBlank(message = "Ingrese el nombre")
    private String nombre;

    @Column(name = "activo",columnDefinition = "BIT")
    private Boolean activo;


    @Getter
    @OneToMany(mappedBy = "tipo", fetch = FetchType.EAGER)
    private List<Departamento> departamentos;

    @Getter
    @ManyToMany
    private List<AutoridadDepartamento> autoridades;

    @Getter @Setter
    @Column(name = "objetivo")
    private String objetivo;

    public TipoDepartamento(){
        this.activo = true;
        this.departamentos = new ArrayList<>();
        this.autoridades = new ArrayList<>();
    }

    public void agregarDepartamento(Departamento departamento){
        this.departamentos.add(departamento);
        departamento.setTipo(this);
    }
}
