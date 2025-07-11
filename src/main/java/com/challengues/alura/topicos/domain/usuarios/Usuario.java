package com.challengues.alura.topicos.domain.usuarios;

import com.challengues.alura.topicos.domain.perfiles.Perfil;
import com.challengues.alura.topicos.domain.perfiles.PerfilRepository;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    @Column(name = "correo_electronico")
    private String correoElectronico;

    private String contrasena;
    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

    private Boolean activo;


    public Usuario(DatosUsuario u, Perfil perfil) {
        this.nombre = u.nombre();
        this.correoElectronico = u.correoElectronico();
        this.contrasena = new BCryptPasswordEncoder().encode(u.contrasena());
        this.perfil = perfil;
        this.activo = true;
    }

    public void update(@Valid DatosActualizacionUsuario datos) {
        if(datos.nombre() != null && !datos.nombre().isEmpty()){
            this.nombre = datos.nombre();
        }
        if(datos.contrasena() != null && !datos.contrasena().isEmpty()){
            this.contrasena = new BCryptPasswordEncoder().encode(datos.contrasena());
        }
    }

    public void delete() {
        this.activo = false;
    }
}
