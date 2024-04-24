set schema 'questionario';
create table if not exists 'usuario'(
    id serial not null,
    login varchar(255),
    senha varchar(255),
    constraint usuario_pk primary key (id)
);
create table if not exists 'questionario' (
    id_questionario serial not null,
    nome varchar(255),
    descricao varchar (255),
    data_criacao date,
    id_usuario_criacao serial,
    constraint questionario_pk primary key (id_questionario),
    constraint usuario_fk foreign key (id_usuario_criacao) references usuario(id)
);

create table if not exists 'resposta' (
    id_resposta serial not null,
    questionario_id serial,
    usuario_id serial,
    data_resposta date,
    constraint resposta_pk primary key (id_resposta),
    constraint usuario_fk foreign key (usuario_id) references usuario(id),
    constraint questionario_fk foreign key (questionario_id) references questionario(id_questionario)
);

create table if not exists 'perguntas' (
    id_perguntas serial not null,
    descricao varchar (255),
    id_questionario serial,
    constraint perguntas_pk primary key (id_perguntas),
    constraint questionario_fk foreign key (questionario_id) references questionario(id_questionario)
);
create table if not exists 'respostas' (
    id_respostas serial not null,
    descricao varchar(255),
    pergunta_id serial,
    constraint respostas_pk primary key (id_respostas),
    constraint pergunta_fk foreign key (pergunta_id) referencess perguntas(id_perguntas)
);