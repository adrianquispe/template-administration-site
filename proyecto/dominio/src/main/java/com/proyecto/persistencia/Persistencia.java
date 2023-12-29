package com.proyecto.persistencia;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;


@MappedSuperclass
public abstract class Persistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Setter @Getter

    private long id;

}
