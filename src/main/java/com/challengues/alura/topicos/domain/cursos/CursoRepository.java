package com.challengues.alura.topicos.domain.cursos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso,Long> {

    Page<Curso> findAllByActivoTrue(Pageable pag);
}
