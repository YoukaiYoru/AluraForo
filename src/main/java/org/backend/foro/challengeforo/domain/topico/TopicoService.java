package org.backend.foro.challengeforo.domain.topico;

import org.backend.foro.challengeforo.domain.curso.Curso;
import org.backend.foro.challengeforo.domain.curso.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository repositorio;


    public Topico actualizarDatos(DatosActualizarTopico actualizarTopico, Long id) {

        Topico topico = topicoRepository.getReferenceById(id);

        if (actualizarTopico.titulo() != null) {
            topico.setTitulo(actualizarTopico.titulo());
        }
        if (actualizarTopico.curso() != null) {
            Curso curso = repositorio.findByTitulo(actualizarTopico.curso());
            topico.setCurso(curso);
        }
        if (actualizarTopico.mensaje() != null) {
            topico.setMensaje(actualizarTopico.mensaje());
        }
        topicoRepository.save(topico);
        return topico;
    }
}
