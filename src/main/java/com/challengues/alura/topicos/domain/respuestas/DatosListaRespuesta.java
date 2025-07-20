package com.challengues.alura.topicos.domain.respuestas;

public record DatosListaRespuesta(
        Long id,
        String mensaje,
        Long id_usuario,
        String solucion
) {
    public DatosListaRespuesta(Respuestas respuestas) {
        this(respuestas.getId(), respuestas.getMensaje(),
                respuestas.getUsuario().getId(), respuestas.getSolucion());
    }
}
