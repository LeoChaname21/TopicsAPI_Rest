package com.challengues.alura.topicos.domain.respuestas;

import com.challengues.alura.topicos.domain.respuestas.validaciones.ValidadordeRespuestas;
import com.challengues.alura.topicos.domain.respuestas.validaciones.ValidadordeRespuestasActualizadas;
import com.challengues.alura.topicos.domain.topicos.DatosActualizacionTopico;
import com.challengues.alura.topicos.domain.topicos.TopicoRepository;
import com.challengues.alura.topicos.domain.usuarios.AuthUtils;
import com.challengues.alura.topicos.domain.usuarios.Usuario;
import com.challengues.alura.topicos.domain.usuarios.UsuarioRepository;
import com.challengues.alura.topicos.infra.exceptions.ValidacionExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespuestasService {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthUtils authUtils;

    @Autowired
    private List<ValidadordeRespuestas> validadordeRespuestas;

    @Autowired
    private List<ValidadordeRespuestasActualizadas> validadordeRespuestasActualizadas;

    public DatosDetalleRespuesta createRespuesta(Long id_topico,DatosRespuestas datos){

        if(!topicoRepository.existsById(id_topico)){
            throw new ValidacionExcepcion("No existe el topico con el id proporcionado");
        }

       // if(!usuarioRepository.existsById(datos.usuario_id())){
         //   throw new ValidacionExcepcion("No existe el usuario con el id proporcionado");
       // }

        var authuser = authUtils.usuarioAutenticado();


        //validar aqui
        validadordeRespuestas.forEach(v->v.validar(id_topico));

        var topico = topicoRepository.getReferenceById(id_topico);

        //var usuario = usuarioRepository.getReferenceById(authuser.getId());

        var respuesta = new Respuestas(datos,topico,authuser);

        respuestaRepository.save(respuesta);

        return new DatosDetalleRespuesta(respuesta);
    }

    public Page<DatosListaRespuesta> showRespuestas(Long id, Pageable pag){
        return respuestaRepository.findTopicosRespuestas(id,pag)
                .map(DatosListaRespuesta::new);
    }

    //verificar que si actualizo un curso de un topico cambie en todas las respuestas de ese topico
    public DatosDetalleRespuesta updateRespuesta(DatosActualizacionRespuesta datosActualizacion){

        if(!respuestaRepository.existsById(datosActualizacion.id())){
            throw new ValidacionExcepcion("No existe una respuesta con el id proporcionado");
        }

        validadordeRespuestasActualizadas.forEach(v->v.validar(datosActualizacion));

        var respuesta = respuestaRepository.getReferenceById(datosActualizacion.id());
        respuesta.update(datosActualizacion);
        return new DatosDetalleRespuesta(respuesta);
    }

    public void deleteRespuesta(Long id){
        var respuesta = respuestaRepository.getReferenceById(id);
        respuesta.delete();
    }

    public DatosDetalleRespuesta showbyId(Long id){
        var respuesta = respuestaRepository.getReferenceById(id);
        return new DatosDetalleRespuesta(respuesta);
    }
}
