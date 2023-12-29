package com.proyecto.model.departamento.autoridadDepartamento;


import com.proyecto.persistencia.Persistencia;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name =  "rol_autoridad")
@Getter @Setter
public class RolAutoridad extends Persistencia {


    @Column(name = "descripción")
    @NotBlank(message = "Ingrese una descripción")
    private String descripcion;

    @Column(name ="activo", columnDefinition = "bit")
    private Boolean activo;

    public RolAutoridad(){
        this.activo = true;
    }

}
