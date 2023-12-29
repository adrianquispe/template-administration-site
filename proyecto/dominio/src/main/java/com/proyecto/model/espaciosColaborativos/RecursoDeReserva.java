package com.proyecto.model.espaciosColaborativos;


import com.proyecto.persistencia.Persistencia;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "recurso_de_reserva")
@Getter
@Setter
public class RecursoDeReserva extends Persistencia {

   @ManyToOne
   @JoinColumn(name = "recurso_id", referencedColumnName = "id")
   private Recurso recurso;

   @Column(name = "cantidad")
   //@NotNull(message = "Campo Obligatorio")
   private Integer cantidad;

   @ManyToOne
   @JoinColumn(name = "reserva_id", referencedColumnName = "id")
   private Reserva reserva;
}
