create table perguntas
(
    uuid_pergunta uuid not null
        primary key,
    enunciado     varchar(255)
);

alter table perguntas
    owner to postgres;

