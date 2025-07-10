package com.challengues.alura.topicos.domain.perfiles;

import org.springframework.beans.factory.annotation.Autowired;
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


}
