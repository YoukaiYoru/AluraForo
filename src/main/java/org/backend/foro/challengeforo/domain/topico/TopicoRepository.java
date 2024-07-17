package org.backend.foro.challengeforo.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Optional<Topico> findByTituloAndMensaje(String título, String mensaje);
    Topico findById(long id);
}
