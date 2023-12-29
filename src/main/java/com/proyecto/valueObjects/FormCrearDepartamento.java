package com.proyecto.valueObjects;

import com.proyecto.model.departamento.Departamento;
import com.proyecto.model.departamento.autoridadDepartamento.AutoridadDepartamento;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class FormCrearDepartamento {

    @Valid
    private Departamento departamento;
    @NotEmpty(message = "El departamento debe contener como mínimo una Autoridad")
    private List<AutoridadDepartamento> autoridades;

    public FormCrearDepartamento(){
        this.autoridades = new ArrayList<>();
    }

}
