package com.challengues.alura.topicos.domain.respuestas;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fecha_creacion,
        LocalDateTime fecha_actualizacion,
        Long id_topico,
        Long id_usuario,
        String solucion
) {
    public DatosDetalleRespuesta(Respuestas respuestas) {
        this(respuestas.getId(), respuestas.getMensaje(), respuestas.getFechaCreacion(),
                respuestas.getFecha_actualizacion(), respuestas.getTopico().getId(),
                respuestas.getUsuario().getId(), respuestas.getSolucion());
    }
}
