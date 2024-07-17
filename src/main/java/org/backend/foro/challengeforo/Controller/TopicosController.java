package org.backend.foro.challengeforo.Controller;


import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.backend.foro.challengeforo.domain.curso.Curso;
import org.backend.foro.challengeforo.domain.curso.CursoRepository;
import org.backend.foro.challengeforo.domain.respuesta.Respuesta;
import org.backend.foro.challengeforo.domain.respuesta.RespuestaRepository;
import org.backend.foro.challengeforo.domain.topico.*;
import org.backend.foro.challengeforo.domain.usuarios.Usuario;
import org.backend.foro.challengeforo.domain.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@ResponseBody
@RequestMapping("/topicos")
@SuppressWarnings("all")
public class TopicosController {
    @Autowired
    private TopicoService topicoService;
    @Autowired
    private TopicoRepository repositorio;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private RespuestaRepository respuestaRepository;

    @Transactional
    @PostMapping
    public ResponseEntity<String> registrarTopico(@Valid @RequestBody DatosTopico topicoRequest, UriComponentsBuilder uriBuilder) {
        Optional<Topico> topicoExistente = repositorio.findByTituloAndMensaje(topicoRequest.titulo(), topicoRequest.mensaje());
        if (topicoExistente.isPresent()) {
            return new ResponseEntity<>("Topico duplicado", HttpStatus.CONFLICT);
        }

        Usuario autor = usuarioRepository.findByNombre(topicoRequest.autor());
        Curso curso = cursoRepository.findByTitulo(topicoRequest.curso());

        Topico nuevoTopico = new Topico();
        nuevoTopico.setTitulo(topicoRequest.titulo());
        nuevoTopico.setMensaje(topicoRequest.mensaje());
        nuevoTopico.setFechaCreacion(LocalDateTime.now());
        nuevoTopico.setStatus(Status.ACTIVO);
        nuevoTopico.setAutor(autor);
        nuevoTopico.setCurso(curso);
        repositorio.save(nuevoTopico);
        URI url = uriBuilder.path("/topicos/{id}").buildAndExpand(nuevoTopico.getId()).toUri();
        return ResponseEntity.created(url).body(nuevoTopico.toString());
    }

    @GetMapping
    public ResponseEntity<List<TopicoInfo>> listarTopicos() {
        return ResponseEntity.ok(repositorio.findAll().stream().map(TopicoInfo::new).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoInfo> obtenerTopico(@PathVariable Long id) {
        Topico topico = repositorio.getReferenceById(id);
        var datosTopico = new TopicoInfo(topico);
        return ResponseEntity.ok(datosTopico);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id ) {
        List<Respuesta> respuestas = respuestaRepository.findByTopicoId(id);
        respuestaRepository.deleteAll(respuestas);
        Topico topico = repositorio.getReferenceById(id);
        repositorio.delete(topico);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizarTopico(@Valid @RequestBody DatosActualizarTopico topicoRequest) {
        Topico topico = topicoService.actualizarDatos(topicoRequest,topicoRequest.id());
        return ResponseEntity.ok(new DatosTopico(topico.getTitulo(),topico.getMensaje(),
                topico.getFechaCreacion(),topico.getStatus().toString(), topico.getAutor().getNombre(),
                topico.getCurso().getTitulo()));
    }




}
