package com.proyecto.model.eventos;

import com.proyecto.persistencia.Persistencia;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "estado_evento")
@Getter
@Setter
public class EstadoEvento extends Persistencia {

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "activo")
    //@NotNull(message = "El Estado es obligatorio")
    private Boolean activo;

    public EstadoEvento(){
        this.activo=true;
    }

}
