package com.challengues.alura.topicos.domain.perfiles;

public record DatosPerfilDetalle(
        Long id,
        String nombre
) {
    public DatosPerfilDetalle(Perfil perfil) {
        this(perfil.getId(),perfil.getNombre());
    }
}
