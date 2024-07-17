package org.backend.foro.challengeforo.domain.respuesta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.backend.foro.challengeforo.domain.topico.Topico;
import org.backend.foro.challengeforo.domain.usuarios.Usuario;

import java.util.Date;

@Table(name = "respuesta")
@Entity(name="Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Respuesta {
    @Id
    @GeneratedValue
    private Long id;
    private Date fechaCreacion;
    private String mensaje;
    private String solucion;

    @ManyToOne
    private Topico topico;

    @ManyToOne
    private Usuario autor;

}
