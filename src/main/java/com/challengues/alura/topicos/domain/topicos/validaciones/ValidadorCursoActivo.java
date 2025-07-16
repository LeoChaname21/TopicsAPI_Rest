package com.challengues.alura.topicos.domain.topicos.validaciones;


import com.challengues.alura.topicos.domain.cursos.CursoRepository;
import com.challengues.alura.topicos.domain.topicos.DatosTopicos;
import com.challengues.alura.topicos.infra.exceptions.ValidacionExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCursoActivo implements ValidadordeTopicos {

    @Autowired
    private CursoRepository cursoRepository;

    public void validar(DatosTopicos datosTopicos){

        var curso = cursoRepository.findActivoById(datosTopicos.curso_id());

        if(!curso){
            throw new ValidacionExcepcion("El curso no esta activado o ha sido eliminado");
        }

    }
}
