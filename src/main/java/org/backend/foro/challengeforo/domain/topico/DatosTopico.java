package org.backend.foro.challengeforo.domain.topico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.Date;

public record DatosTopico(
    @NotBlank
    String titulo,

    @NotBlank
    String mensaje,

    @Future
    LocalDateTime fecha,

    String status,

    @NotBlank
    String autor,

    @NotBlank
    String curso

) {

    public DatosTopico(Topico topico){
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                topico.getStatus().toString(), topico.getAutor().getId().toString(),topico.getCurso().getTitulo());
    }
}
