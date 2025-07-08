package com.challengues.alura.topicos.domain.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Long,Usuario> {
}
