ALTER TABLE respuesta
    DROP FOREIGN KEY respuesta_ibfk_1;

ALTER TABLE respuesta
    ADD CONSTRAINT respuesta_ibfk_1
        FOREIGN KEY (topico_id) REFERENCES topico(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE;
