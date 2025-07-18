package com.challengues.alura.topicos.domain.topicos;

import com.challengues.alura.topicos.domain.cursos.Curso;
import com.challengues.alura.topicos.domain.respuestas.Respuestas;
import com.challengues.alura.topicos.domain.usuarios.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
@Table(name = "topicos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "topico",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Respuestas> respuestas;

    public Topico(DatosTopicos t,Usuario usuario,Curso curso) {
        this.titulo = t.titulo();
        this.mensaje = t.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = true;
        this.usuario = usuario;
        this.curso = curso;
    }

    public void delete() {
        this.status = false;
    }

    public void update(@Valid DatosActualizacionTopico datos,Curso curso) {

        if(datos.titulo() != null && !datos.titulo().isEmpty()){
            this.titulo = datos.titulo();
        }

        if(datos.mensaje()!=null && !datos.mensaje().isEmpty()){
            this.mensaje = datos.mensaje();
        }

        if(datos.curso_id()!=null){
            this.curso = curso;
        }

    }
}
