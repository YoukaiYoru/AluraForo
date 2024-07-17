CREATE TABLE curso (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       titulo VARCHAR(255) NOT NULL,
                       categoria VARCHAR(255) NOT NULL
);

CREATE TABLE Perfil (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nombre VARCHAR(255) NOT NULL
);

CREATE TABLE Usuario_Perfil (
                                usuario_id BIGINT NOT NULL,
                                perfil_id BIGINT NOT NULL,
                                PRIMARY KEY (usuario_id, perfil_id),
                                FOREIGN KEY (usuario_id) REFERENCES usuario(id),
                                FOREIGN KEY (perfil_id) REFERENCES Perfil(id)
);

CREATE TABLE Topico (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        titulo VARCHAR(255) NOT NULL,
                        mensaje TEXT NOT NULL,
                        fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        status VARCHAR(50) NOT NULL,
                        autor_id BIGINT NOT NULL,
                        curso_id BIGINT NOT NULL,
                        FOREIGN KEY (autor_id) REFERENCES Usuario(id),
                        FOREIGN KEY (curso_id) REFERENCES Curso(id)
);

CREATE TABLE Respuesta (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           mensaje TEXT NOT NULL,
                           topico_id BIGINT NOT NULL,
                           fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           autor_id BIGINT NOT NULL,
                           solucion BOOLEAN DEFAULT FALSE,
                           FOREIGN KEY (topico_id) REFERENCES Topico(id),
                           FOREIGN KEY (autor_id) REFERENCES Usuario(id)
);

INSERT INTO Curso (titulo, categoria) VALUES ('Curso de Java', 'Programación');
INSERT INTO Perfil (nombre) VALUES ('Administrador');
INSERT INTO Usuario_Perfil (usuario_id, perfil_id) VALUES (1, 1); -- Asigna el perfil con id 1 al usuario con id 1
INSERT INTO Topico (titulo, mensaje, status, autor_id, curso_id) VALUES ('Nuevo Tópico', 'Este es el mensaje del tópico', 'ACTIVO', 1, 1);
INSERT INTO Respuesta (mensaje, topico_id, autor_id) VALUES ('Esta es una respuesta al tópico', 1, 1);


