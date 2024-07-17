package org.backend.foro.challengeforo.domain.perfil;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.backend.foro.challengeforo.domain.usuarios.Usuario;

import java.util.List;


@Table(name = "perfil")
@Entity(name = "Perfil")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Perfil {
    @Id
    @GeneratedValue
    private long id;

    @ManyToMany
    private List<Usuario> usuarios;
}
