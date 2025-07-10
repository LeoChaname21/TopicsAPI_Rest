ALTER TABLE perfiles ADD activo tinyint not null;

ALTER TABLE usuarios ADD activo tinyint not null;

ALTER TABLE respuestas ADD status tinyint not null;