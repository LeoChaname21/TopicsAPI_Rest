package com.challengues.alura.topicos.controller;

import com.challengues.alura.topicos.domain.usuarios.DatosUsuario;
import com.challengues.alura.topicos.domain.usuarios.LoginUsuario;
import com.challengues.alura.topicos.domain.usuarios.Usuario;
import com.challengues.alura.topicos.domain.usuarios.UsuarioService;
import com.challengues.alura.topicos.infra.security.UsuarioToken;
import com.challengues.alura.topicos.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/auth")
public class AutenticacionController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    @Transactional
    @PostMapping("/register")
    public ResponseEntity registrar(@RequestBody @Valid DatosUsuario datosUsuario, UriComponentsBuilder uriComponentsBuilder){
        var usuario = usuarioService.registrar(datosUsuario);
        var uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.id()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginUsuario datos){

        var tokenAutenticacion = new UsernamePasswordAuthenticationToken(datos.correoElectronico(),datos.contrasena());

        var autenticacion = manager.authenticate(tokenAutenticacion);

        var tokenJwto = tokenService.generarToken((Usuario) autenticacion.getPrincipal());

        var tokenDto = new UsuarioToken(tokenJwto);
        return ResponseEntity.ok(tokenDto);

    }





}
