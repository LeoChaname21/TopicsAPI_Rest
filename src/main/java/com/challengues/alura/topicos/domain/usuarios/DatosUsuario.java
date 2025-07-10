package com.challengues.alura.topicos.domain.usuarios;

import com.challengues.alura.topicos.domain.perfiles.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosUsuario(
        @NotBlank String nombre,
        @NotBlank @Email String correoElectronico,
        @NotBlank String contrasena,
        @NotNull Perfil perfil
) {
}
