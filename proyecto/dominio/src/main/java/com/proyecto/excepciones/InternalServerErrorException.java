package com.proyecto.excepciones;

public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(String mensaje) {
        super(mensaje);
    }
}
