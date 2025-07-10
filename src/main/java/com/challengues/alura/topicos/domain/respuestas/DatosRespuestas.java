package com.challengues.alura.topicos.domain.respuestas;

import com.challengues.alura.topicos.domain.topicos.Topico;
import com.challengues.alura.topicos.domain.usuarios.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRespuestas(
    @NotBlank String mensaje,
    @NotNull Topico idtopico,
    @NotNull Usuario idusuario,
    @NotBlank String solucion
) {
}
