package com.challengues.alura.topicos.domain.respuestas;

import jakarta.validation.constraints.NotNull;

public record DatosActualizacionRespuesta(
        @NotNull Long id,
        String mensaje,
        String solucion
) {
}
