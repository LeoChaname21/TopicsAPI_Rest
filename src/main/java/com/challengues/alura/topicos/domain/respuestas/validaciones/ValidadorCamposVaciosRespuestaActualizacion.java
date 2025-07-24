package com.challengues.alura.topicos.domain.respuestas.validaciones;

import com.challengues.alura.topicos.domain.respuestas.DatosActualizacionRespuesta;
import com.challengues.alura.topicos.infra.exceptions.ValidacionExcepcion;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCamposVaciosRespuestaActualizacion implements ValidadordeRespuestasActualizadas{

    public void validar(DatosActualizacionRespuesta datos) {

        var mensaje = datos.mensaje()!=null && !datos.mensaje().isBlank();
        var solucion = datos.solucion()!=null && !datos.solucion().isBlank();

        if(!mensaje&&!solucion){
            throw new ValidacionExcepcion("No hay campos validos para actualizar");
        }

    }
}
