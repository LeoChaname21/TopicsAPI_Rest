package com.challengues.alura.topicos.domain.usuarios;

import com.challengues.alura.topicos.domain.perfiles.PerfilRepository;
import com.challengues.alura.topicos.infra.exceptions.ValidacionExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    public DatosUsuarioDetalle registrar(DatosUsuario datosUsuario){

        if(!perfilRepository.existsById(datosUsuario.id_perfil())){
            throw new ValidacionExcepcion("No existe el perfil con el id proporcionado");
        }

        var perfil = perfilRepository.getReferenceById(datosUsuario.id_perfil());

        var usuario = new Usuario(datosUsuario,perfil);
        usuarioRepository.save(usuario);
        return new DatosUsuarioDetalle(usuario);
    }

    public Page<DatosUsuarioLista> showUsuarios(Pageable pag){
        return usuarioRepository.findAll(pag)
                .map(DatosUsuarioLista::new);
    }

    public DatosUsuarioDetalle updateUsuarios(DatosActualizacionUsuario datos){
        var usuario = usuarioRepository.getReferenceById(datos.id());
        usuario.update(datos);
        return new DatosUsuarioDetalle(usuario);
    }

    public void deleteUsuarios(Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.delete();
    }

    public DatosUsuarioDetalle showById(Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        return new DatosUsuarioDetalle(usuario);
    }



}
