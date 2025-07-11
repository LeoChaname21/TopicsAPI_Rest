package com.challengues.alura.topicos.domain.usuarios;

import jakarta.validation.constraints.NotNull;

public record DatosActualizacionUsuario(
        @NotNull Long id,
        String nombre,
        String contrasena
) {
}
