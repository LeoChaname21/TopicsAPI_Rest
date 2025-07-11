package com.challengues.alura.topicos.domain.topicos.validaciones;

import com.challengues.alura.topicos.domain.topicos.DatosActualizacionTopico;
import com.challengues.alura.topicos.domain.topicos.TopicoRepository;
import com.challengues.alura.topicos.infra.exceptions.ValidacionExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorDuplicadoTituloyMensajeActualizacion implements ValidadordeTopicosActualizados{

    @Autowired
    private TopicoRepository topicoRepository;

    public void validar(DatosActualizacionTopico datosActualizacionTopico){
        var existetopico = topicoRepository.existsByTituloAndMensaje(datosActualizacionTopico.titulo(),
                datosActualizacionTopico.mensaje());

        if(existetopico){
            throw new ValidacionExcepcion("El titulo y el mensaje del topico ya existe");
        }

    }
}
