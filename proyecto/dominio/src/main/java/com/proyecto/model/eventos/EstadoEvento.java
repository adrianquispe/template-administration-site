package com.proyecto.model.eventos;

import com.proyecto.persistencia.Persistencia;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "estado_evento")
@Getter
@Setter
public class EstadoEvento extends Persistencia {

    @Column(name = "fecha", columnDefinition = "datetime")
    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    private ValorEstado valor;

    public EstadoEvento(){
        this.fecha=LocalDateTime.now();
    }

    public EstadoEvento(ValorEstado valor){
        this.valor = valor;
        this.fecha = LocalDateTime.now();
    }

}
