package com.proyecto.model.socio.estadoSocio;

import com.proyecto.model.socio.Socio;
import com.proyecto.persistencia.Persistencia;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name = "Estado_socio")
@Getter @Setter
public class EstadoSocio extends Persistencia {

    @Column(name = "Fecha")
    private LocalDate fecha;

    @Column(name = "Activo")
    @NotNull(message = "El Estado es obligatorio")
    private Boolean activo;

    @OneToOne
    @JoinColumn(name = "socio_id", referencedColumnName = "id")
    @NotNull(message = "Campo Obligatorio")
    private Socio socio;

    public EstadoSocio(){
        this.fecha=LocalDate.now();
    }
}