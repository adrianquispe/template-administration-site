package com.proyecto.model.eventos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ParticipanteEventoTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeAll
    public static void setUp() {
        emf = Persistence.createEntityManagerFactory("nombre_de_tu_unidad_de_persistencia");
        em = emf.createEntityManager();
    }

    @AfterAll
    public static void tearDown() {
        em.close();
        emf.close();
    }

    static class Persistencia {
        static void guardar(ParticipanteEvento participante, EntityManager em) {
            // Lógica para guardar el participante en la base de datos
        }
    }

    @Test
    void testGuardarParticipanteEvento() {
        // Arrange
        ParticipanteEvento participante = new ParticipanteEvento();
        participante.setNombre("John");
        participante.setApellido("Doe");

        // Act
        Persistencia.guardar(participante, em);

        // Assert
        assertTrue(participante.getId() > 0);
    }

    @Test
    void testValidacionCamposObligatorios() {
        // Arrange
        ParticipanteEvento participante = new ParticipanteEvento();

        // Act & Assert
        assertThrows(Exception.class, () -> Persistencia.guardar(participante, em));
    }
}


