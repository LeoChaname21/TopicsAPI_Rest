package com.challengues.alura.topicos.domain.usuarios;

import com.challengues.alura.topicos.domain.perfiles.Perfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correoElectronico;
    private String contrasena;
    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

    private Boolean activo;

    public Usuario(DatosUsuario u) {
        this.nombre = u.nombre();
        this.correoElectronico = u.correoElectronico();
        this.contrasena = u.contrasena();
        this.perfil = u.perfil();
        this.activo = true;
    }
}
