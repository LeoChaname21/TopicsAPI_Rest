package com.challengues.alura.topicos.domain.perfiles;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "perfiles")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Boolean activo;

    public Perfil(DatosPerfil p) {
        this.nombre = p.nombre();
        this.activo = true;
    }

    public void update(@Valid DatosActualizacionPerfil datosActualizacion) {

        if(datosActualizacion.nombre() != null && !datosActualizacion.nombre().isEmpty()){
            this.nombre = datosActualizacion.nombre();
        }
    }

    public void delete() {
        this.activo = false;
    }
}
