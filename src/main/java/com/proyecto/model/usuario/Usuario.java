package com.proyecto.model.usuario;

import com.proyecto.model.socio.Rol;
import com.proyecto.persistencia.Persistencia;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Usuarios")
@Getter @Setter
public class Usuario extends Persistencia {

    @Column(name = "nombre_del_suario")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "El nombre de usuario debe contener solo letras")
    @Size(min = 3, message = "El nombre de usuario debe tener al menos 3 letras")
    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String nombreUsuario;

    @Column(name = "contraseña")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!]).{8,}$", message = "La contraseña debe contener al menos 1 mayúscula, 1 minúscula, 1 número, 1 carácter especial y tener al menos 8 caracteres")
    @NotBlank(message = "La contraseña es obligatoria")
    private String contrasenia;

    @Enumerated(EnumType.STRING)
    //@NotBlank(message = "El rol es obligatorio")
    private Rol rol;

    @Column(name ="nombre")
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Column(name ="apellido")
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Column(name ="dni")
    @NotBlank(message = "El dni es obligatorio")
    private String dni;

    public Usuario(){
        this.rol = Rol.ASOCIADO;
    }

    public String nombreCompleto(){
        return this.nombre + " " + this.apellido;
    }
}