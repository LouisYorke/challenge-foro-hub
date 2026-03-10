package com.challenge.foro_hub.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopic(
        @NotNull Long id,
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull StatusTopic status,
        @NotNull Long idCurso
) {}