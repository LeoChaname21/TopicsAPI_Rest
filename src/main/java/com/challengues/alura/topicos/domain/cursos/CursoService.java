package com.challengues.alura.topicos.domain.cursos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public DatosDetalleCurso createCurso(DatosCurso datosCurso){
        var curso = new Curso(datosCurso);
        cursoRepository.save(curso);
        return new DatosDetalleCurso(curso);
    }

    public Page<DatosListaCurso> showCursos(Pageable pag){
        return cursoRepository.findAllByActivoTrue(pag)
                .map(DatosListaCurso::new);
    }

    public DatosDetalleCurso UpdateCurso(DatosActualizacionCurso datos){
        var curso = cursoRepository.getReferenceById(datos.id());
        curso.update(datos);
        return new DatosDetalleCurso(curso);
    }

    public void delete(Long id){
        var curso = cursoRepository.getReferenceById(id);
        curso.eliminar();
    }

    public DatosDetalleCurso showbyId(Long id){
        var curso = cursoRepository.getReferenceById(id);
        return new DatosDetalleCurso(curso);
    }

}
