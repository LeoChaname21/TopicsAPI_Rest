package com.challengues.alura.topicos.controller;

import com.challengues.alura.topicos.domain.respuestas.DatosRespuestas;
import com.challengues.alura.topicos.domain.respuestas.RespuestasService;
import com.challengues.alura.topicos.domain.topicos.DatosActualizacionTopico;
import com.challengues.alura.topicos.domain.topicos.DatosTopicos;
import com.challengues.alura.topicos.domain.topicos.TopicoService;
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
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private RespuestasService respuestasService;

    @Transactional
    @PostMapping
    public ResponseEntity createTopicos(@RequestBody @Valid DatosTopicos datosTopicos){
        var topico = topicoService.createTopico(datosTopicos);
        return ResponseEntity.ok(topico);
    }

    @GetMapping
    public ResponseEntity showTopicos(@PageableDefault(size = 10,sort = {"fechaCreacion"},direction = Sort.Direction.ASC) Pageable pag){
        var topicos = topicoService.showTopicos(pag);
        return ResponseEntity.ok(topicos);
    }

    @Transactional
    @PutMapping
    public ResponseEntity updateTopicos(@RequestBody @Valid DatosActualizacionTopico datosActualizacion){
        var topico = topicoService.updateTopico(datosActualizacion);
        return ResponseEntity.ok(topico);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTopicos(@PathVariable Long id){
        topicoService.deleteTopico(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity showById(@PathVariable Long id){
        var topico = topicoService.showById(id);
        return ResponseEntity.ok(topico);
    }

    //respuestas

    @Transactional
    @PostMapping("/{id}/respuestas")
    public ResponseEntity createRespuesta(@PathVariable Long id, @RequestBody @Valid DatosRespuestas datosRespuestas, UriComponentsBuilder uriComponentsBuilder){

        var respuesta = respuestasService.createRespuesta(id,datosRespuestas);

        var uri = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.id()).toUri();

        return ResponseEntity.created(uri).body(respuesta);
    }

    @GetMapping("/{id}/respuestas")
    public ResponseEntity showRespuestasById(@PathVariable Long id,Pageable pag){
        var respuestas = respuestasService.showRespuestas(id,pag);
        return ResponseEntity.ok(respuestas);
    }



}
