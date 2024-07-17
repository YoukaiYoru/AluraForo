package org.backend.foro.challengeforo.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.backend.foro.challengeforo.domain.curso.Curso;

public record DatosActualizarTopico(
        @NotNull
        Long id,
        @NotBlank
        String titulo,
        @NotBlank
        String curso,
        @NotBlank
        String mensaje
) {
}
