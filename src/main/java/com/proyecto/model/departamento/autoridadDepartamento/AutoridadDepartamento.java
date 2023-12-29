package com.proyecto.model.departamento.autoridadDepartamento;

import com.proyecto.model.usuario.Usuario;
import com.proyecto.persistencia.Persistencia;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "autoridad_departamento")
@Getter @Setter
public class AutoridadDepartamento extends Persistencia {

    @ManyToOne
    @JoinColumn(name = "rol_id", referencedColumnName = "id")
    private RolAutoridad rol;

    @Column(name = "activo", columnDefinition = "bit")
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    public AutoridadDepartamento(){
        this.activo = true;
    }

    public String nombreAutoridad(){
        return this.usuario.getNombre() + " " + this.usuario.getApellido();
    }
}
