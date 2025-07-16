package com.challengues.alura.topicos.domain.respuestas.validaciones;

import com.challengues.alura.topicos.domain.respuestas.DatosRespuestas;
import com.challengues.alura.topicos.domain.topicos.TopicoRepository;
import com.challengues.alura.topicos.infra.exceptions.ValidacionExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorTopicoActivo implements ValidadordeRespuestas{

    @Autowired
    private TopicoRepository topicoRepository;

    public void validar(Long id_topico){

        var topicoactivo = topicoRepository.findStatusById(id_topico);

        if(!topicoactivo){
            throw new ValidacionExcepcion("El topico ha sido eliminado o no existe");
        }

    }
}
