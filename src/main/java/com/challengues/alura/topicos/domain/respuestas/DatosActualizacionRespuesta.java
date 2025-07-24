package com.challengues.alura.topicos.domain.respuestas;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosActualizacionRespuesta(
        @NotNull Long id,
        String mensaje,
        String solucion
) {
}
