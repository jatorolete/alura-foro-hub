package com.alura.forohub.domain.usuario;

import jakarta.validation.constraints.Email;

public record DatosActualizarUsuario(
        String nombre,
        @Email String correoElectronico,
        String contrasena
) {}