package com.challengues.alura.topicos.domain.topicos;

import java.time.LocalDateTime;

public record DatosListaTopico(
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        Long id_usuario,
        Long id_curso
) {
    public DatosListaTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(),
                topico.getFechaCreacion(),topico.getUsuario().getId(),topico.getCurso().getId());
    }
}
