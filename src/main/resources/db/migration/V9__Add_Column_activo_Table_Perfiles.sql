ALTER TABLE perfiles ADD activo tinyint;
UPDATE perfiles SET activo = 1;
ALTER TABLE perfiles MODIFY activo tinyint not null;