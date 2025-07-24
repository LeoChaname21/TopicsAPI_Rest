package com.challengues.alura.topicos.domain.topicos;

import java.time.LocalDateTime;

public record DatosListaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha_creacion,
        LocalDateTime fecha_Actualizacion,
        Long id_usuario,
        Long id_curso
) {
    public DatosListaTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFechaCreacion(),topico.getFecha_actualizacion(),
                topico.getUsuario().getId(),topico.getCurso().getId());
    }
}
