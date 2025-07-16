package com.challengues.alura.topicos.domain.respuestas;

public record DatosDetalleRespuesta(
        Long id,
        String mensaje,
        Long id_topico,
        Long id_usuario,
        String solucion
) {
    public DatosDetalleRespuesta(Respuestas respuestas) {
        this(respuestas.getId(), respuestas.getMensaje(), respuestas.getTopico().getId(),
                respuestas.getUsuario().getId(), respuestas.getSolucion());
    }
}
