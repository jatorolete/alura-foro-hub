package com.alura.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        Long id,
        String titulo,
        String mensaje,
        String status,
        Long cursoId
) {}