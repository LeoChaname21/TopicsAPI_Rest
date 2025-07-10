package com.challengues.alura.topicos.domain.cursos;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cursos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Boolean activo;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public Curso(DatosCurso c) {
        this.nombre = c.nombre();
        this.categoria = c.categoria();
        this.activo = true;
    }

    public void update(@Valid DatosActualizacionCurso datos) {
        //se podria añadir una validacion
        if(datos.nombre() != null && !datos.nombre().isEmpty()){
            this.nombre = datos.nombre();
        }

        if(datos.categoria() != null){
            this.categoria = datos.categoria();
        }
    }

    public void eliminar() {
        this.activo = false;
    }
}
