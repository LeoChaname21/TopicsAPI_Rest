package com.challengues.alura.topicos.domain.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginUsuario(
        @NotBlank @Email String correoElectronico,
        @NotBlank String contrasena
) {
}
