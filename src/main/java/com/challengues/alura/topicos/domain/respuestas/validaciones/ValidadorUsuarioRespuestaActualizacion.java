package com.challengues.alura.topicos.domain.respuestas.validaciones;

import com.challengues.alura.topicos.domain.respuestas.DatosActualizacionRespuesta;
import com.challengues.alura.topicos.domain.respuestas.RespuestaRepository;
import com.challengues.alura.topicos.domain.usuarios.AuthUtils;
import com.challengues.alura.topicos.infra.exceptions.ValidacionExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorUsuarioRespuestaActualizacion implements ValidadordeRespuestasActualizadas{

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private AuthUtils authUtils;

    public void validar(DatosActualizacionRespuesta datos) {

        var respuesta = respuestaRepository.getReferenceById(datos.id());

        var authUser = authUtils.usuarioAutenticado();

        if(!respuesta.getUsuario().getId().equals(authUser.getId())){
            throw new ValidacionExcepcion("No se puede actualizar una respuesta que no es suya");
        }


    }
}
