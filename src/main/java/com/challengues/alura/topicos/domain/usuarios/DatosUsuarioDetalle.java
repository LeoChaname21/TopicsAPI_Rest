package com.challengues.alura.topicos.domain.usuarios;

import com.challengues.alura.topicos.domain.perfiles.Perfil;

public record DatosUsuarioDetalle(
        Long id,
        String nombre,
        String correoElectronico
) {
    public DatosUsuarioDetalle(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getCorreoElectronico());
    }
}