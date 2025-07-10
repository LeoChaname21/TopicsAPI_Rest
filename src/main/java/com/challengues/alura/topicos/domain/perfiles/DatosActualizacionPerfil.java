package com.challengues.alura.topicos.domain.perfiles;

import jakarta.validation.constraints.NotNull;

public record DatosActualizacionPerfil(
        @NotNull Long id,
        String nombre
) {
}
