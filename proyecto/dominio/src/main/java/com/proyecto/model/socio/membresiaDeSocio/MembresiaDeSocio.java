package com.proyecto.model.socio.membresiaDeSocio;

import java.time.LocalDate;

import com.proyecto.persistencia.Persistencia;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "Membresia_de_socio")
public class MembresiaDeSocio extends Persistencia {

    @Column(name = "cuota_social")
    @NotNull(message = "La cuota social es obligatoria")
    private Double cuotaSocial;

    @Column(name = "duracion")
    @NotNull(message = "Ingrese la duración")
    private int duracion;

    @Column(name = "fecha_de_asociacion")
    private LocalDate fechaDeAsociacion;

    @Column(name = "plazo_antes_de_vencer")
    @NotNull(message = "Campo Obligatorio")
    private int plazoAntesDeVencer;

    @Column(name = "dias_para_vencer")
    @NotNull(message = "Campo Obligatorio")
    private int diasParaVencer;

    @ManyToOne
    @JoinColumn(name = "membresia_id", referencedColumnName = "id")
    @NotBlank(message = "La membresía es obligatoria")
    private Membresia membresia;


    public MembresiaDeSocio(double cuotaSocial, int duracion, LocalDate fechaDeAsociacion, int plazoAntesDeVencer, int diasParaVencer, Membresia membresia){
        this.cuotaSocial = cuotaSocial;
        this.duracion = duracion;
        this.fechaDeAsociacion = LocalDate.now();
        this.plazoAntesDeVencer = plazoAntesDeVencer;
        this.diasParaVencer =  diasParaVencer;
        this.membresia = membresia;
    }
    public MembresiaDeSocio(){};
    @Override
    public String toString() {
        return "MembresiaDeSocio{" +
                ", cuotaSocal=" + cuotaSocial +
                ", duracion=" + duracion +
                ", fechaDeAsociacion=" + fechaDeAsociacion +
                ", plazoAntesDeVencer=" + plazoAntesDeVencer +
                ", diasParaVencer=" + diasParaVencer +
                ", membresia=" + membresia +
                '}';
    }
}


