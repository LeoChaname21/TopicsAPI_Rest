package com.challengues.alura.topicos.domain.perfiles;

public record DatosPerfilDetalle(
        String nombre,
        Boolean activo
) {
    public DatosPerfilDetalle(Perfil perfil) {
        this(perfil.getNombre(),perfil.getActivo());
    }
}
