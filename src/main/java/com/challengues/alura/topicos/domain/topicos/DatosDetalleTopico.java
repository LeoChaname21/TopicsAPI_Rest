package com.challengues.alura.topicos.domain.topicos;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha_creacion,
        LocalDateTime fecha_actualizacion,
        Long id_usuario,
        Long id_curso
) {
    public DatosDetalleTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFechaCreacion(),topico.getFecha_actualizacion(),
                topico.getUsuario().getId(),topico.getCurso().getId());
    }
}
