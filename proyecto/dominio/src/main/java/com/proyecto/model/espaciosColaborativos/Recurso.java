package com.proyecto.model.espaciosColaborativos;

import com.proyecto.persistencia.Persistencia;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "recurso")
@Getter
@Setter
public class Recurso extends Persistencia {

    @Column(name = "nombre")
    @NotNull(message = "Campo Obligatorio")
    private String nombre;
}
