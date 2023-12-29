package com.proyecto.model.eventos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EventoTest {

    private Evento evento;

    @BeforeEach
    void setUp() {
        evento = new Evento();
    }

    @Test
    void agregarParticipante() {
        ParticipanteEvento participante1 = new ParticipanteEvento();
        ParticipanteEvento participante2 = new ParticipanteEvento();

        evento.agregarParticipante(participante1, participante2);

        assertEquals(2, evento.getParticipantes().size());
        assertTrue(evento.getParticipantes().contains(participante1));
        assertTrue(evento.getParticipantes().contains(participante2));
    }

    @Test
    void estadoActual() {
        EstadoEvento estado1 = new EstadoEvento();
        EstadoEvento estado2 = new EstadoEvento();

        evento.getEstados().add(estado1);
        evento.getEstados().add(estado2);

        EstadoEvento estadoActual = evento.estadoActual();

        assertEquals(estado2, estadoActual);
    }
}