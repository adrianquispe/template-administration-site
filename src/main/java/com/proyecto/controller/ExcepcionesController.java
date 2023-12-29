package com.proyecto.controller;

import com.proyecto.excepciones.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExcepcionesController {

    @ExceptionHandler(EntityNotFoundException.class)//404
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> manejarEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>("Recurso no encontrado: " + (ex.getMessage()),  HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)//400
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> manejarBadRequestException(BadRequestException ex) {
        return new ResponseEntity<>("Solicitud incorrecta", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerErrorException.class)//500
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> manejarInternalServerErrorException(InternalServerErrorException ex) {
        return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(UnauthorizedException.class)//401
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> manejarUnauthorizedException(UnauthorizedException ex) {
        return new ResponseEntity<>("No autorizado", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ForbiddenException.class)//403
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<String> manejarForbiddenException(ForbiddenException ex) {
        return new ResponseEntity<>("Acceso prohibido", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ConflictException.class)//409
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> manejarConflictException(ConflictException ex) {
        return new ResponseEntity<>("Conflicto", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ServiceUnavailableException.class)//503
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ResponseEntity<String> manejarServiceUnavailableException(ServiceUnavailableException ex) {
        return new ResponseEntity<>("Servicio no disponible", HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(BadGatewayException.class)//502
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ResponseEntity<String> manejarBadGatewayException(BadGatewayException ex) {
        return new ResponseEntity<>("Pasarela incorrecta", HttpStatus.BAD_GATEWAY);
    }
}