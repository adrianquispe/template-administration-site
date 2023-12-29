package com.proyecto.model.socio.ubicacion;

import com.proyecto.persistencia.Persistencia;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Ubicacion")
@Setter @Getter
public class Ubicacion extends Persistencia {


    @Column(name = "provincia")
    //@NotBlank(message = "Ingrese la provincia")
    private String provincia;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "municipio")
    private String municipio;

    @Column(name = "calle")
    //@NotBlank(message = "Ingrese la calle")
    private String calle;

    @Column(name = "altura")
    //@Pattern(regexp = "^[0-9]+$", message = "La altura debe contener solo dígitos")
    private String altura;

    @Column(name = "codigo_postal")
    //@NotBlank(message = "Ingrese el código postal")
    @Pattern(regexp = "^[0-9]+$", message = "El código postal debe contener solo dígitos")
    private String codigoPostal;

    @Column(name = "piso")
    //@NotBlank(message = "Ingrese el número de piso")
    @Pattern(regexp = "^[0-9]+$", message = "El piso debe contener solo dígitos")
    private String piso;

    @Column(name = "barrio")
    private String barrio;

}