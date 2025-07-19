package com.challengues.alura.topicos.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.challengues.alura.topicos.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generarToken(Usuario usuario){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Topicos.byLeo")
                    .withSubject(usuario.getCorreoElectronico())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algorithm);
        }catch (JWTCreationException ex){
            throw new RuntimeException("Error al general el token",ex);
        }
    }

    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.of("-05:00"));
    }


}
