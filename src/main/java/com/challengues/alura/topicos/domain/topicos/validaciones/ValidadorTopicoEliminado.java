package com.challengues.alura.topicos.domain.topicos.validaciones;

import com.challengues.alura.topicos.domain.topicos.DatosActualizacionTopico;
import com.challengues.alura.topicos.domain.topicos.TopicoRepository;
import com.challengues.alura.topicos.infra.exceptions.ValidacionExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorTopicoEliminado implements ValidadordeTopicosActualizados{

    @Autowired
    private TopicoRepository topicoRepository;

    public void validar(DatosActualizacionTopico datos){

        var topicoActivo = topicoRepository.findStatusById(datos.id());

        if(!topicoActivo){
            throw new ValidacionExcepcion("El topico ha sido eliminado");
        }

    }


}
