package com.challengues.alura.topicos.domain.usuarios;

public record DatosUsuarioLista(
        Long id,
        String nombre,
        String correoElectronico,
        Long id_perfil,
        Boolean activo
) {
    public DatosUsuarioLista(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getCorreoElectronico(), usuario.getPerfil().getId(),
                usuario.getActivo());
    }
}
