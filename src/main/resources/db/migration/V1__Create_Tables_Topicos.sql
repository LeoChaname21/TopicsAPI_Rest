CREATE TABLE cursos(
    id bigint not null auto_increment,
    nombre varchar(200) not null unique,
    categoria varchar(200) not null unique,
    primary key(id)
);

CREATE TABLE perfiles(
    id bigint not null auto_increment,
    nombre varchar(200) not null,

    primary key(id)
);

CREATE TABLE usuarios(
    id bigint not null auto_increment,
    nombre varchar(250) not null,
    correoElectronico varchar(250) not null unique,
    contrasena varchar(200) not null,
    perfil_id bigint not null,

    primary key(id),
    constraint fk_usuarios_perfil_id foreign key(perfil_id) references perfiles(id)
);

CREATE TABLE topicos(
    id bigint not null auto_increment,
    titulo varchar(200) not null,
    mensaje varchar(255) not null,
    fechaCreacion Datetime not null,
    status tinyint not null,
    usuario_id bigint not null,
    curso_id bigint not null,

    primary key(id),
    constraint fk_topicos_usuario_id foreign key(usuario_id) references usuarios(id),
    constraint fk_topicos_curso_id foreign key(curso_id) references cursos(id)
);

CREATE TABLE respuestas(
    id bigint not null auto_increment,
    mensaje varchar(250) not null,
    topico_id bigint not null,
    fechaCreacion Datetime not null,
    usuario_id bigint not null,
    solucion varchar(1000) not null,

    primary key(id),
    constraint fk_respuestas_topico_id foreign key(topico_id) references topicos(id),
    constraint fk_respuestas_usuario_id foreign key(usuario_id) references usuarios(id)


);