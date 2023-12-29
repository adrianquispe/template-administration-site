package com.proyecto.model.espaciosColaborativos;


import com.proyecto.model.departamento.Departamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Entity
@Table(name = "reserva")
@Getter @Setter
public class Reserva {

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @GeneratedValue(generator = "uuid2")
    private UUID uuid;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "espacio_id", referencedColumnName = "id")
    private EspacioColaborativo espacio;

    @OneToMany(mappedBy = "reserva", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
    private List<RecursoDeReserva> recursosDeReserva;

    @Column(name = "fecha_solicitada")
    //@NotNull(message = "Campo Obligatorio")
    private LocalDate fechaSolicitada;

    @Enumerated(EnumType.STRING)
    private ValorEstado estado;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "reserva_id")
    private List<EstadoReserva> estados;

    @Column(name = "referente_nombre")
    @NotBlank(message = "Campo Obligatorio")
    private String referenteNombre;

    @Column(name = "referente_apellido")
    @NotBlank(message = "Campo Obligatorio")
    private String referenteApellido;

    @Column(name = "referente_mail")
    @Pattern(regexp = "^[\\w\\.-]+@[\\w\\.-]+\\.\\w+", message = "El formato del correo no es válido. Ejemplo@asd.com ")
    @NotBlank(message = "Campo Obligatorio")
    private String referenteMail;

    @ManyToOne
    @JoinColumn(name = "departamento_id", referencedColumnName = "id")
    private Departamento referenteDepartamento;

    @Column(name="hora_inicio")
    @NotNull(message = "Campo Obligatorio")
    private LocalTime horaInicio;

    @Column(name="hora_fin")
    @NotNull(message = "Campo Obligatorio")
    private LocalTime horaFin;

    public Reserva(){
        this.estado = ValorEstado.PENDIENTE_DE_CONFIRMACION;
        this.recursosDeReserva = new ArrayList<>();
    }

    public void agregarEstado(EstadoReserva estadoReserva){
        this.estado = estadoReserva.getValor();
        this.estados.add(estadoReserva);
    }

    public void agregarRecursoDeReserva(RecursoDeReserva... recursoDeReservas){
        Collections.addAll(this.recursosDeReserva, recursoDeReservas);
    }

    public Boolean pendiente(){
        return this.estado == ValorEstado.PENDIENTE_DE_CONFIRMACION;
    }

    public Boolean rechazado(){
        return this.estado == ValorEstado.RECHAZADO;
    }

    public Boolean confirmado(){
        return this.estado == ValorEstado.CONFIRMADO;
    }

    public Boolean confirmadoParcialmente(){
        return this.estado == ValorEstado.CONFIRMADO_PARCIALMENTE;
    }

    public EstadoReserva ultimoEstado() {
        if (this.estados != null && !this.estados.isEmpty()) {
            int ultimo = estados.size() - 1;
            return estados.get(ultimo);
        } else {
            return null;
        }
    }

    public String motivoEstado(){
        EstadoReserva ultimoEstado = this.ultimoEstado();
        if(ultimoEstado != null){
            return ultimoEstado.getMotivo();
        }else{
            return "No existe motivo";
        }
    }
}
