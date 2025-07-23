package com.challengues.alura.topicos.controller;

import com.challengues.alura.topicos.domain.respuestas.DatosActualizacionRespuesta;
import com.challengues.alura.topicos.domain.respuestas.RespuestasService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private RespuestasService respuestasService;

    @Transactional
    @PutMapping
    public ResponseEntity updateRespuestas(@RequestBody @Valid DatosActualizacionRespuesta datosActualizacion){
        var respuesta =  respuestasService.updateRespuesta(datosActualizacion);
        return ResponseEntity.ok(respuesta);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteRespuesta(@PathVariable Long id){
        respuestasService.deleteRespuesta(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity showById(@PathVariable Long id){
        var respuesta = respuestasService.showbyId(id);
        return ResponseEntity.ok(respuesta);
    }


}
