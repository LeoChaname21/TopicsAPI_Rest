package com.challengues.alura.topicos.domain.respuestas;

import java.time.LocalDateTime;

public record DatosListaRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fecha_creacion,
        LocalDateTime fecha_actualizacion,
        Long id_usuario,
        String solucion
) {
    public DatosListaRespuesta(Respuestas respuestas) {
        this(respuestas.getId(), respuestas.getMensaje(),respuestas.getFechaCreacion(),
                respuestas.getFecha_actualizacion(), respuestas.getUsuario().getId(),
                respuestas.getSolucion());
    }
}
