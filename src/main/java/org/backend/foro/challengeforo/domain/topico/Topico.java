package org.backend.foro.challengeforo.domain.topico;


import jakarta.persistence.*;
import lombok.*;
import org.backend.foro.challengeforo.domain.curso.Curso;
import org.backend.foro.challengeforo.domain.curso.CursoRepository;
import org.backend.foro.challengeforo.domain.respuesta.Respuesta;
import org.backend.foro.challengeforo.domain.usuarios.Usuario;
import org.backend.foro.challengeforo.domain.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Table(name = "topico")
@Entity(name="Topico")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Respuesta> respuesta;






}
