package com.challenge.foro_hub.domain.topic;

import com.challenge.foro_hub.domain.curso.Curso;
import com.challenge.foro_hub.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopic(@NotBlank String titulo,
                                 @NotBlank String mensaje,
                                 @NotNull Long idCurso,
                                 @NotNull Long idUsuario) {
}
