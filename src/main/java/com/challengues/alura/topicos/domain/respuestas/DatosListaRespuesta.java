package com.challengues.alura.topicos.domain.respuestas;

public record DatosListaRespuesta(
        String mensaje,
        Long id_usuario,
        String solucion
) {
    public DatosListaRespuesta(Respuestas respuestas) {
        this(respuestas.getMensaje(),
                respuestas.getUsuario().getId(), respuestas.getSolucion());
    }
}
