package com.proyecto.model.eventos;

import com.proyecto.model.socio.Socio;
import com.proyecto.persistencia.Persistencia;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "participante_evento")
@Getter
@Setter
public class ParticipanteEvento extends Persistencia {

    @Column(name = "nombre")
    @NotBlank(message = "Campo Obligatorio")
    private String nombre;

    @Column(name = "apellido")
    @NotBlank(message = "Campo Obligatorio")
    private String apellido;

    @ManyToOne
    @JoinColumn(name = "socio_id", referencedColumnName = "id")
    //@NotBlank(message = "Campo Obligatorio")
    private Socio socio;

    // Este atributo es para aquellos participantes que no seleccionen
    // ningun socio, pero si la opcion "otros" e ingresen un valor alli.
    @Column(name = "otros")
    private String otros;

    @Column(name = "mail")
    @Pattern(regexp = "^[\\w\\.-]+@[\\w\\.-]+\\.\\w+", message = "El formato del correo no es válido")
    @NotNull(message = "Campo Obligatorio")
    private String mail;
    public ParticipanteEvento(){

    }

}
