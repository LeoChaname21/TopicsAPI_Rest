package com.challengues.alura.topicos.controller;

import com.challengues.alura.topicos.domain.cursos.CursoService;
import com.challengues.alura.topicos.domain.cursos.DatosActualizacionCurso;
import com.challengues.alura.topicos.domain.cursos.DatosCurso;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Transactional
    @PostMapping
    public ResponseEntity createmedico(@RequestBody @Valid DatosCurso datosCurso, UriComponentsBuilder uriComponentsBuilder) {

        var detallecurso = cursoService.createCurso(datosCurso);
        var uri = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(detallecurso.id()).toUri();
        return ResponseEntity.created(uri).body(detallecurso);
    }

    @GetMapping
    public ResponseEntity showCursos(@PageableDefault(sort = {"categoria"}, size = 10) Pageable pag) {
        var cursos = cursoService.showCursos(pag);
        return ResponseEntity.ok(cursos);
    }

    @Transactional
    @PutMapping
    public ResponseEntity updateCursos(@RequestBody @Valid DatosActualizacionCurso datos) {
        var curso = cursoService.UpdateCurso(datos);
        return ResponseEntity.ok(curso);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCursos(@PathVariable Long id){
        cursoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity showbyid(@PathVariable Long id){
        var curso = cursoService.showbyId(id);
        return ResponseEntity.ok(curso);
    }



}
