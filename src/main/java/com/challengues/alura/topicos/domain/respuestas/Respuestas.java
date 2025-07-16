package com.challengues.alura.topicos.domain.respuestas;

import com.challengues.alura.topicos.domain.topicos.Topico;
import com.challengues.alura.topicos.domain.usuarios.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "respuestas")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuestas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String solucion;

    private Boolean status;


    public Respuestas(DatosRespuestas r, Topico topico, Usuario usuario) {
        this.mensaje = r.mensaje();
        this.topico = topico;
        this.fechaCreacion = LocalDateTime.now();
        this.usuario = usuario;
        this.solucion = r.solucion();
        this.status = true;
    }


    public void update(@Valid DatosActualizacionRespuesta datosActualizacion) {
        if(datosActualizacion.mensaje() != null && !datosActualizacion.mensaje().isEmpty()){
            this.mensaje = datosActualizacion.mensaje();
        }

        if (datosActualizacion.solucion() != null && !datosActualizacion.solucion().isEmpty()) {
            this.solucion = datosActualizacion.solucion();
        }
    }

    public void delete() {
        this.status = false;
    }
}
