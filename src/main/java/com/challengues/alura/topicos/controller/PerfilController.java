package com.challengues.alura.topicos.controller;

import com.challengues.alura.topicos.domain.perfiles.DatosActualizacionPerfil;
import com.challengues.alura.topicos.domain.perfiles.DatosPerfil;
import com.challengues.alura.topicos.domain.perfiles.PerfilService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/perfiles")
@SecurityRequirement(name = "bearer-key")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @Transactional
    @PostMapping
    public ResponseEntity createPerfil(@RequestBody @Valid DatosPerfil datosPerfil, UriComponentsBuilder uriComponentsBuilder){
        var perfil = perfilService.createPerfil(datosPerfil);
        var uri = uriComponentsBuilder.path("/perfiles/{id}").buildAndExpand(perfil.id()).toUri();
        return ResponseEntity.created(uri).body(perfil);
    }

    @GetMapping
    public ResponseEntity showPerfil(@PageableDefault(sort = {"id"} , direction = Sort.Direction.DESC,size = 10) Pageable pag){
        var perfiles = perfilService.showPerfil(pag);
        return ResponseEntity.ok(perfiles);
    }

    @Transactional
    @PutMapping
    public ResponseEntity updatePerfil(@RequestBody @Valid DatosActualizacionPerfil datos){
        var perfil = perfilService.updatePerfil(datos);
        return ResponseEntity.ok(perfil);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deletePerfil(@PathVariable Long id){
        perfilService.deletePerfil(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity showbyId(@PathVariable Long id){
        var perfil = perfilService.showById(id);
        return ResponseEntity.ok(perfil);
    }



}
