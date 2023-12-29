package com.proyecto.model.socio.membresiaDeSocio;

import com.proyecto.persistencia.Persistencia;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Membresia")
@Getter @Setter
public class Membresia extends Persistencia {

    @Column(name = "nombre")
    @NotBlank(message = "Ingrese el nombre")
    private String nombre;

}