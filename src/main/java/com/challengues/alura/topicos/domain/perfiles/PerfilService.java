package com.challengues.alura.topicos.domain.perfiles;

import com.challengues.alura.topicos.domain.cursos.DatosActualizacionCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    public DatosPerfilDetalle createPerfil(DatosPerfil datosPerfil){
        var perfil = new Perfil(datosPerfil);
        perfilRepository.save(perfil);
        return new DatosPerfilDetalle(perfil);
    }

    public Page<DatosListaPerfil> showPerfil(Pageable pag){
        return perfilRepository.findAll(pag)
                .map(DatosListaPerfil::new);
    }

    public DatosPerfilDetalle updatePerfil(DatosActualizacionPerfil datos){
        var perfil = perfilRepository.getReferenceById(datos.id());
        perfil.update(datos);
        return new DatosPerfilDetalle(perfil);
    }


    public DatosPerfilDetalle showById(Long id) {
        var perfil = perfilRepository.getReferenceById(id);
        return new DatosPerfilDetalle(perfil);
    }

    public void deletePerfil(Long id){
        var perfil = perfilRepository.getReferenceById(id);
        perfil.delete();
    }

}
