ALTER TABLE respuestas ADD fecha_actualizacion Datetime;

UPDATE respuestas SET fecha_actualizacion = fecha_creacion;

ALTER TABLE respuestas MODIFY fecha_actualizacion Datetime not null;

