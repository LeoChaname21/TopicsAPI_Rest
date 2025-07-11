package com.challengues.alura.topicos.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GestorErrores {

    @ExceptionHandler(ValidacionExcepcion.class)
    public ResponseEntity gestionarerrorValidacion(ValidacionExcepcion ex){
        Map<String,String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity gestionarerror404(EntityNotFoundException ex){
        Map<String,String> error = new HashMap<>();
        error.put("error","El recurso con el id proporcionado no existe o ha sido eliminado");
        return ResponseEntity.badRequest().body(error);
    }
}
