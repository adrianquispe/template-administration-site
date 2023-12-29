package com.proyecto.model.espaciosColaborativos;

import com.proyecto.persistencia.Persistencia;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "espacio_colaborativo")
@Getter
@Setter
public class EspacioColaborativo extends Persistencia {

    @Column(name = "nombre")
    @NotNull(message = "Campo Obligatorio")
    private String nombre;

    @Column(name = "descripcion")
    @NotNull(message = "Campo Obligatorio")
    private String descripcion;

}
