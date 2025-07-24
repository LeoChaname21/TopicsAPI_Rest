package com.challengues.alura.topicos.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity gestionarError409(MethodArgumentNotValidException ex){
        var errores = ex.getFieldErrors();
        return ResponseEntity.badRequest()
                .body(errores.stream()
                        .map(DatosErrorValidacion::new)
                        .collect(Collectors.toList()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity gestionarErrorEntidad(DataIntegrityViolationException ex){
        Map<String,String> error = new HashMap<>();
        String errorMessage = ex.getMostSpecificCause().getMessage().toLowerCase();
        //poner en los que son unicos
        if(errorMessage.contains("duplicate entry")){
            if(errorMessage.contains("nombre")){
                errorMessage = "Ya esta registrado ese nombre";
            } else if (errorMessage.contains("correo")) {
                errorMessage = "Ya esta registrado ese correo";
            }
        }
        error.put("error",errorMessage);
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity gestionarerrorEnums(HttpMessageNotReadableException ex){

        Map<String,String> error = new HashMap<>();
        String errorValidacion = ex.getMostSpecificCause().getMessage();
        String lista = errorValidacion.split("\\[")[1].split("\\]")[0];
        if(errorValidacion.contains("Categoria")){
            errorValidacion = "La categoria ha sigo mal escrita o no existe, solo esta permitido: "+lista;
        }
        error.put("error",errorValidacion);
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity errorCredenciales(){
        Map<String,String> error = new HashMap<>();
        error.put("error","credenciales incorrectas");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    public record DatosErrorValidacion(String campo, String mensaje){
        public DatosErrorValidacion(FieldError fielderror){
            this(fielderror.getField(),fielderror.getDefaultMessage());
        }

    }


}
