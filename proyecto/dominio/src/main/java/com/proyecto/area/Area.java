package com.proyecto.area;

import com.proyecto.persistencia.Persistencia;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Area")
@Getter @Setter

public class Area extends Persistencia {

    @Column(name = "nombre")
    @NotBlank(message = "Ingrese el nombre de la área")
    private String nombre;
}


