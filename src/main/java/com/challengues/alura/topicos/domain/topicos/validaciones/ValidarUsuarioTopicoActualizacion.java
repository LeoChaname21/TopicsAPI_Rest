package com.challengues.alura.topicos.domain.topicos.validaciones;

import com.challengues.alura.topicos.domain.topicos.DatosActualizacionTopico;
import com.challengues.alura.topicos.domain.topicos.TopicoRepository;
import com.challengues.alura.topicos.domain.usuarios.AuthUtils;
import com.challengues.alura.topicos.infra.exceptions.ValidacionExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarUsuarioTopicoActualizacion implements ValidadordeTopicosActualizados{

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private AuthUtils authUtils;

    public void validar(DatosActualizacionTopico datos) {

        var topico = topicoRepository.getReferenceById(datos.id());
        var authUser = authUtils.usuarioAutenticado();

        if(!topico.getUsuario().getId().equals(authUser.getId())){
            throw new ValidacionExcepcion("No se puede actualizar un topico que no es suyo");
        }

    }
}
