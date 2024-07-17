package org.backend.foro.challengeforo.domain.topico;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.backend.foro.challengeforo.domain.curso.Curso;
import org.backend.foro.challengeforo.domain.usuarios.Usuario;

import java.time.LocalDateTime;

public record TopicoInfo(
        long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String status,
        String autor,
        String curso
) {
    public TopicoInfo(Topico topico){
        this(   topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus().toString(),
                topico.getAutor().getNombre(),
                topico.getCurso().getTitulo());
    }
}
