package com.challengues.alura.topicos.domain.respuestas.validaciones;

import com.challengues.alura.topicos.domain.respuestas.DatosActualizacionRespuesta;
import com.challengues.alura.topicos.domain.respuestas.RespuestaRepository;
import com.challengues.alura.topicos.domain.topicos.TopicoRepository;
import com.challengues.alura.topicos.infra.exceptions.ValidacionExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarTopicoActivoActualizacion implements ValidadordeRespuestasActualizadas{

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    public void validar(DatosActualizacionRespuesta datosActualizacion){

        var respuesta = respuestaRepository.getReferenceById(datosActualizacion.id());

        var topicoActivo = topicoRepository.findStatusById(respuesta.getTopico().getId());

        if(!topicoActivo){
            throw new ValidacionExcepcion("El topico ha sido eliminado");
        }
    }

}
