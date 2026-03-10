package com.alura.forohub.infra.security;

import com.alura.forohub.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {
        Algorithm algorithm = Algorithm.HMAC256(apiSecret);

        return JWT.create()
                .withIssuer("forohub")
                .withSubject(usuario.getCorreoElectronico())
                .withExpiresAt(generarFechaExpiracion())
                .sign(algorithm);
    }

    private Instant generarFechaExpiracion() {
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String token) {
        Algorithm algorithm = Algorithm.HMAC256(apiSecret);
        return JWT.require(algorithm)
                .withIssuer("forohub")
                .build()
                .verify(token)
                .getSubject();
    }
}