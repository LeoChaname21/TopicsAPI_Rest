package com.challengues.alura.topicos.domain.topicos;

import com.challengues.alura.topicos.domain.cursos.Curso;
import com.challengues.alura.topicos.domain.usuarios.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosTopicos(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        //LocalDateTime fechaCreacion, no tendria que pasarlo, se deberia crear en el constructor
        @NotNull Long usuario_id,
        @NotNull Long curso_id
) {
}
