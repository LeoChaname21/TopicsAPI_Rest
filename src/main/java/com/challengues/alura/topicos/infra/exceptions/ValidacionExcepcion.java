package com.challengues.alura.topicos.infra.exceptions;

public class ValidacionExcepcion extends RuntimeException {

    public ValidacionExcepcion(String mensaje){
        super(mensaje);
    }
}
