package com.challengues.alura.topicos.domain.topicos;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        Long id_usuario,
        Long id_curso
) {
    public DatosDetalleTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFechaCreacion(),topico.getUsuario().getId(),topico.getCurso().getId());
    }
}
