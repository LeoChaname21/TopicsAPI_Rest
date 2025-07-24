ALTER TABLE topicos ADD fecha_actualizacion Datetime;

UPDATE topicos SET fecha_actualizacion = fecha_creacion;

ALTER TABLE topicos MODIFY fecha_actualizacion Datetime not null;

