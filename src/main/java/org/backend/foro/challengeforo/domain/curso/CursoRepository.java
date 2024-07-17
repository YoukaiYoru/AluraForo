package org.backend.foro.challengeforo.domain.curso;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Curso findByTitulo(String nombre);
}
