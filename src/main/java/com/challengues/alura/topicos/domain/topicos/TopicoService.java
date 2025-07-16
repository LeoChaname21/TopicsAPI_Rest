package com.challengues.alura.topicos.domain.topicos;

import com.challengues.alura.topicos.domain.cursos.Curso;
import com.challengues.alura.topicos.domain.cursos.CursoRepository;
import com.challengues.alura.topicos.domain.topicos.validaciones.ValidadordeTopicos;
import com.challengues.alura.topicos.domain.topicos.validaciones.ValidadordeTopicosActualizados;
import com.challengues.alura.topicos.domain.usuarios.UsuarioRepository;
import com.challengues.alura.topicos.infra.exceptions.ValidacionExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private List<ValidadordeTopicos> validadordeTopicos;

    @Autowired
    private List<ValidadordeTopicosActualizados> validadordeTopicosActualizados;

    public DatosDetalleTopico createTopico(DatosTopicos datosTopicos){

        if(!usuarioRepository.existsById(datosTopicos.usuario_id())){
            throw new ValidacionExcepcion("No existe un usuario con el id proporcionado");
        }

        if(!cursoRepository.existsById(datosTopicos.curso_id())){
            throw new ValidacionExcepcion("No existe un curso con el id proporcionado");
        }

        validadordeTopicos.forEach(v->v.validar(datosTopicos));

        var usuario = usuarioRepository.getReferenceById(datosTopicos.usuario_id());

        var curso = cursoRepository.getReferenceById(datosTopicos.curso_id());

        var topico = new Topico(datosTopicos,usuario,curso);

        topicoRepository.save(topico);

        return new DatosDetalleTopico(topico);

    }

    public Page<DatosListaTopico> showTopicos(Pageable pag){
        return topicoRepository.findAllByStatusTrue(pag)
                .map(DatosListaTopico::new);
    }

    public DatosDetalleTopico updateTopico(DatosActualizacionTopico datos){

        if(!topicoRepository.existsById(datos.id())){
            throw new ValidacionExcepcion("No existe un topico con el id proporcionado");
        }

        validadordeTopicosActualizados.forEach(v->v.validar(datos));

        var topico = topicoRepository.getReferenceById(datos.id());
        Curso curso = null;
        if(datos.curso_id()!=null){
            if(!cursoRepository.existsById(datos.curso_id())){
                throw new ValidacionExcepcion("No existe un curso con el id proporcionado");
            }
            curso = cursoRepository.getReferenceById(datos.curso_id());
        }
        topico.update(datos,curso);
        return new DatosDetalleTopico(topico);
    }

    public void deleteTopico(Long id){
        var topico = topicoRepository.getReferenceById(id);
        topico.delete();
    }

    public DatosDetalleTopico showById(Long id){
        var topico = topicoRepository.getReferenceById(id);
        return new DatosDetalleTopico(topico);
    }

}
