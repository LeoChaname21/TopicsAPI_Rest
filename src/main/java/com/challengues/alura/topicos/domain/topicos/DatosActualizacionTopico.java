package com.challengues.alura.topicos.domain.topicos;

import jakarta.validation.constraints.NotNull;

public record DatosActualizacionTopico(
        @NotNull Long id,
        String titulo,
        String mensaje,
        Long curso_id
) {
}
