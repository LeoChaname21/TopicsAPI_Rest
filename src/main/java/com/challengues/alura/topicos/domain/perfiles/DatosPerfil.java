package com.challengues.alura.topicos.domain.perfiles;

import jakarta.validation.constraints.NotBlank;

public record DatosPerfil(
        @NotBlank String nombre
) {
}
