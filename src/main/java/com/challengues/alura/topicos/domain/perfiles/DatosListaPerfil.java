package com.challengues.alura.topicos.domain.perfiles;

public record DatosListaPerfil(
        Long id,
        String nombre
) {
    public DatosListaPerfil(Perfil perfil) {
        this(perfil.getId(),perfil.getNombre());
    }
}
