package com.challengues.alura.topicos.domain.perfiles;

public record DatosPerfilDetalle(
        Long id,
        String nombre,
        Boolean activo
) {
    public DatosPerfilDetalle(Perfil perfil) {
        this(perfil.getId(),perfil.getNombre(),perfil.getActivo());
    }
}
