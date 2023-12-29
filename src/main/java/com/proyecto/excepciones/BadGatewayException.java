package com.proyecto.excepciones;

public class BadGatewayException extends RuntimeException {
    public BadGatewayException(String mensaje) {
        super(mensaje);
    }
}
