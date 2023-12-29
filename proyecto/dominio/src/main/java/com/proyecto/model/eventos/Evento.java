package com.proyecto.model.eventos;

import com.proyecto.model.departamento.Departamento;
import com.proyecto.model.socio.ubicacion.Ubicacion;
import com.proyecto.persistencia.Persistencia;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
//import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "evento")
public class Evento {

    @Getter
    @Setter
    @Column(name = "imagen")
    private String imagen;

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")//VARCHAR(36) para almacenar UUIDs como cadenas
    @GeneratedValue(generator = "uuid2")
    @Getter
    //@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Getter
    @Setter
    @Column(name = "nombre")
    @NotNull(message = "Campo Obligatorio")
    private String nombre;

    @Getter
    @Setter
    @Column(name = "descripcion")
    @NotNull(message = "Campo Obligatorio")
    private String descripcion;

    @Getter
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id")
    private List<ParticipanteEvento> participantes;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ubicacion_id")
    //@Valid
    //@NotNull(message = "Campo Obligatorio")
    private Ubicacion ubicacion;

    @Getter
    @Setter
    @Column(name = "fecha_hora_inicio")
    @NotNull(message = "Campo Obligatorio")
    private LocalDateTime fechaHoraInicio;

    @Getter
    @Setter
    @Column(name = "fecha_hora_fin")
    @NotNull(message = "Campo Obligatorio")
    private LocalDateTime fechaHoraFin;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private ValorEstado estado;

    @Getter
    @Setter
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id")
    private List<EstadoEvento> estados;

    @Getter
    @Setter
    @Column(name = "link")
    private String link;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "departamento_id", referencedColumnName = "id")
    //@NotNull(message = "Campo Obligatorio")
    private Departamento departamentoOrganizador;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoEvento tipo;

    @Getter
    @Setter
    @Column(name = "link_reunion")
    private String linkReunion;


    public Evento(){
        this.estados = new ArrayList<>();
        this.participantes = new ArrayList<>();
        this.estado = ValorEstado.ACTIVO;
    }

    public void agregarParticipante(ParticipanteEvento... participante){
        Collections.addAll(this.participantes, participante);
    }

    public String linkFormulario(){
        return "localhost:/8080/inscripcion/" + this.id;
    }

    public void agregarEstado(EstadoEvento estado){
        this.estados.add(estado);
    }

    public Boolean virtual(){
        return this.tipo == TipoEvento.VIRTUAL;
    }

    public Boolean hibrido(){
        return this.tipo == TipoEvento.HIBRIDO;
    }

    public Boolean presencial(){
        return this.tipo == TipoEvento.PRESENCIAL;
    }
    public Boolean activo(){
        return this.estado == ValorEstado.ACTIVO;
    }

    public Boolean finalizado(){
        return this.estado == ValorEstado.FINALIZADO;
    }

    public Boolean cancelado(){
        return this.estado == ValorEstado.CANCELADO;
    }
}
