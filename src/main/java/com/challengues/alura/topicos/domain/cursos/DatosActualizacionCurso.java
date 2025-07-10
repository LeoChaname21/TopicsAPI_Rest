package com.challengues.alura.topicos.domain.cursos;

import jakarta.validation.constraints.NotNull;

public record DatosActualizacionCurso(
        @NotNull Long id,
        String nombre,
        Categoria categoria
) {
}
