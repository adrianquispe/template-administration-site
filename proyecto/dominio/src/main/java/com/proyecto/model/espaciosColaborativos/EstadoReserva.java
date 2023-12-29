package com.proyecto.model.espaciosColaborativos;


import com.proyecto.persistencia.Persistencia;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Table(name = "estado_reserva")
@Getter
@Setter
public class EstadoReserva extends Persistencia {

   @Enumerated(EnumType.STRING)
   private ValorEstado valor;

   @Column(name = "motivo", columnDefinition = "text")
   //@NotNull(message = "Campo Obligatorio")
   private String motivo;

   @Column(name = "fecha_hora", columnDefinition = "datetime")
   //@NotNull(message = "Campo Obligatorio")
   private LocalDateTime fechaHora;

   public EstadoReserva(){
      this.fechaHora = LocalDateTime.now();
   }

   public EstadoReserva(ValorEstado valor){
      this.valor = valor;
      this.fechaHora = LocalDateTime.now();
   }

}
