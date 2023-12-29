package com.proyecto.tipoDepartamento;

import com.proyecto.persistencia.Persistencia;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TipoDepartamento")
@Setter @Getter
public class TipoDepartamento extends Persistencia{

    @Column(name = "nombre")
    @NotBlank(message = "Ingrese el nombre")
    private String nombre;


}
