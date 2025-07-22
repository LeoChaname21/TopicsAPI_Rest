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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
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
        this.contrasena = this.encript(u.contrasena());
        this.perfil = perfil;
        this.activo = true;
    }

    private String encript(String contrasena){
        return new BCryptPasswordEncoder().encode(contrasena);
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


    //security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+this.perfil.getNombre()));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return correoElectronico;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
