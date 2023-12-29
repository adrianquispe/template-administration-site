package com.proyecto.model.Area;

import com.proyecto.persistencia.Persistencia;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "area")
@Setter
@Getter
public class Area extends Persistencia {

    @Column(name="nombre")
    @NotBlank(message = "Campo Obligatorio")
    private String nombre;


    public Area(Long idArea, String nombre){
        this.nombre = nombre;
    }

    public Area(){}

}