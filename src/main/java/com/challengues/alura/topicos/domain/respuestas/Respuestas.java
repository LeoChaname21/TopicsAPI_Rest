package com.challengues.alura.topicos.domain.respuestas;

import com.challengues.alura.topicos.domain.topicos.Topico;
import com.challengues.alura.topicos.domain.usuarios.Usuario;
import jakarta.persistence.*;
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
    @GeneratedValue
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


    public Respuestas(DatosRespuestas r) {
        this.mensaje = r.mensaje();
        this.topico = r.idtopico();
        this.fechaCreacion = LocalDateTime.now();
        this.usuario = r.idusuario();
        this.solucion = r.solucion();
        this.status = true;
    }


}
