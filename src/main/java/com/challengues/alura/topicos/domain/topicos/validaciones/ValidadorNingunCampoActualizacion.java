package com.challengues.alura.topicos.domain.topicos.validaciones;

import com.challengues.alura.topicos.domain.topicos.DatosActualizacionTopico;
import com.challengues.alura.topicos.infra.exceptions.ValidacionExcepcion;
import org.springframework.stereotype.Component;

@Component
public class ValidadorNingunCampoActualizacion implements ValidadordeTopicosActualizados{

    public void validar(DatosActualizacionTopico datos) {

        var tituloValido = datos.titulo()!=null && !datos.titulo().isBlank();
        var mensajeValido = datos.mensaje() != null && !datos.mensaje().isBlank();
        var cursovalido = datos.curso_id() != null;

        if(!tituloValido&&!mensajeValido&&!cursovalido){
            throw new ValidacionExcepcion("No hay ningun campo valido para actualizar");
        }

    }
}
