package com.challengues.alura.topicos.domain.cursos;


public record DatosListaCurso(
        Long id,
        String nombre,
        Categoria categoria
) {
    public DatosListaCurso(Curso curso) {
        this(curso.getId(),
                curso.getNombre(),
                curso.getCategoria());
    }
}
