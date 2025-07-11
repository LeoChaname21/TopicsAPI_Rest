package com.challengues.alura.topicos.controller;

import com.challengues.alura.topicos.domain.usuarios.DatosActualizacionUsuario;
import com.challengues.alura.topicos.domain.usuarios.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity showUsuarios(@PageableDefault(size = 10,sort = {"id"}, direction = Sort.Direction.DESC) Pageable pag){
        var usuarios = usuarioService.showUsuarios(pag);
        return ResponseEntity.ok(usuarios);
    }

    @Transactional
    @PutMapping
    public ResponseEntity updateUsuarios(@RequestBody @Valid DatosActualizacionUsuario datosActualizacion){
        var usuario = usuarioService.updateUsuarios(datosActualizacion);
        return ResponseEntity.ok(usuario);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUsuarios(@PathVariable Long id){
        usuarioService.deleteUsuarios(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity showbyId(@PathVariable Long id){
        var usuario = usuarioService.showById(id);
        return ResponseEntity.ok(usuario);
    }




}
