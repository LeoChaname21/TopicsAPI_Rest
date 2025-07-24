package com.challengues.alura.topicos.domain.topicos;

import com.challengues.alura.topicos.domain.cursos.Curso;
import com.challengues.alura.topicos.domain.cursos.CursoRepository;
import com.challengues.alura.topicos.domain.topicos.validaciones.ValidadordeTopicos;
import com.challengues.alura.topicos.domain.topicos.validaciones.ValidadordeTopicosActualizados;
import com.challengues.alura.topicos.domain.usuarios.AuthUtils;
import com.challengues.alura.topicos.domain.usuarios.Usuario;
import com.challengues.alura.topicos.domain.usuarios.UsuarioRepository;
import com.challengues.alura.topicos.infra.exceptions.ValidacionExcepcion;
import com.challengues.alura.topicos.infra.security.SecurityFilter;
import com.challengues.alura.topicos.infra.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthUtils authUtils;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private List<ValidadordeTopicos> validadordeTopicos;

    @Autowired
    private List<ValidadordeTopicosActualizados> validadordeTopicosActualizados;

    public DatosDetalleTopico createTopico(DatosTopicos datosTopicos){

        //agregar validaciones creo del idusuario
        //por ejemplo si se acabo su token
        //token a probar luego de 4 h
        //eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBUEkgVG9waWNvcy5ieUxlbyIsInN1YiI6InN1c2FuYUBnbWFpbC5jb20iLCJpZCI6MjIsImV4cCI6MTc1MzMwOTIxMX0.XgXw0B0e2w5xI1Kj8-uLbOb02Au3KjbRlWsRjMIF4B4

        var authuser = authUtils.usuarioAutenticado();

        if(!cursoRepository.existsById(datosTopicos.curso_id())){
            throw new ValidacionExcepcion("No existe un curso con el id proporcionado");
        }

        validadordeTopicos.forEach(v->v.validar(datosTopicos));

        //var usuario = usuarioRepository.getReferenceById(authuser.getId());

        var curso = cursoRepository.getReferenceById(datosTopicos.curso_id());

        var topico = new Topico(datosTopicos,authuser,curso);

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

        if(datos.curso_id()!=null && !cursoRepository.existsById(datos.curso_id())){
            throw new ValidacionExcepcion("No existe un curso con el id proporcionado");
        }

        validadordeTopicosActualizados.forEach(v->v.validar(datos));

        var topico = topicoRepository.getReferenceById(datos.id());
        Curso curso = null;
        if(datos.curso_id()!=null){
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
