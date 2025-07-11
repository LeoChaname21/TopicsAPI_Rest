package com.challengues.alura.topicos.domain.topicos.validaciones;

import com.challengues.alura.topicos.domain.topicos.DatosTopicos;
import com.challengues.alura.topicos.domain.topicos.TopicoRepository;
import com.challengues.alura.topicos.infra.exceptions.ValidacionExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorDuplicadoTituloyMensaje implements ValidadordeTopicos{

    @Autowired
    private TopicoRepository topicoRepository;

    public void validar(DatosTopicos datosTopicos){
        var existetopico = topicoRepository.existsByTituloAndMensaje(datosTopicos.titulo(), datosTopicos.mensaje());

        if(existetopico){
            throw new ValidacionExcepcion("El titulo y el mensaje del topico ya existe");
        }

    }

}
