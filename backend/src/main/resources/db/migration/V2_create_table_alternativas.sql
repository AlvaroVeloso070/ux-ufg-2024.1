create table alternativas
(
    correta          boolean      not null,
    uuid_alternativa uuid         not null
        primary key,
    uuid_pergunta    uuid
        constraint fkbrorhgyec6c254ke0o0e4nwxr
            references perguntas,
    descricao        varchar(255) not null
);

alter table alternativas
    owner to postgres;