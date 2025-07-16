package com.challengues.alura.topicos.domain.topicos.validaciones;


import com.challengues.alura.topicos.domain.cursos.CursoRepository;
import com.challengues.alura.topicos.domain.topicos.DatosActualizacionTopico;
import com.challengues.alura.topicos.infra.exceptions.ValidacionExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCursoActivoActualizacion implements ValidadordeTopicosActualizados {

    @Autowired
    private CursoRepository cursoRepository;

    public void validar(DatosActualizacionTopico datosActualizacionTopico){

        var curso = cursoRepository.findActivoById(datosActualizacionTopico.curso_id());

        if(!curso){
            throw new ValidacionExcepcion("El curso no esta activado o ha sido eliminado");
        }

    }
}
