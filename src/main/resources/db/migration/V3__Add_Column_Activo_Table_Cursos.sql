ALTER TABLE cursos ADD activo tinyint;

UPDATE cursos SET activo = 1;

ALTER TABLE cursos MODIFY activo tinyint not null;