package com.challengues.alura.topicos.domain.respuestas.validaciones;

import com.challengues.alura.topicos.domain.respuestas.DatosActualizacionRespuesta;
import com.challengues.alura.topicos.domain.respuestas.RespuestaRepository;
import com.challengues.alura.topicos.infra.exceptions.ValidacionExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarRespuestaActiva implements ValidadordeRespuestasActualizadas{

    @Autowired
    private RespuestaRepository respuestaRepository;

    public void validar(DatosActualizacionRespuesta datosActualizacionRespuesta){

        var respuestaActiva = respuestaRepository.findStatusById(datosActualizacionRespuesta.id());

        if(!respuestaActiva){
            throw new ValidacionExcepcion("La respuesta ha sido eliminada");
        }

    }
}
