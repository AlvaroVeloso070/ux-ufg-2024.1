CREATE TABLE alternativas
(
    uuid_alternativa UUID         NOT NULL,
    descricao        VARCHAR      NOT NULL,
    correta          BOOLEAN      NOT NULL,
    uuid_pergunta    UUID         NOT NULL,
    CONSTRAINT pk_alternativas PRIMARY KEY (uuid_alternativa)
);

CREATE TABLE jogadores
(
    uuid_jogador UUID             NOT NULL,
    nome         VARCHAR(255)     NOT NULL,
    pontuacao    DOUBLE PRECISION NOT NULL,
    CONSTRAINT pk_jogadores PRIMARY KEY (uuid_jogador)
);

CREATE TABLE perguntas
(
    uuid_pergunta UUID NOT NULL,
    enunciado     VARCHAR NOT NULL,
    categoria     VARCHAR NOT NULL,
    dificuldade   VARCHAR NOT NULL,
    CONSTRAINT pk_perguntas PRIMARY KEY (uuid_pergunta)
);

CREATE TABLE respostas
(
    uuid_resposta    UUID NOT NULL,
    uuid_pergunta    UUID NOT NULL,
    uuid_alternativa UUID NOT NULL,
    uuid_jogador     UUID NOT NULL,
    tempo_resposta   BIGINT NOT NULL,
    CONSTRAINT pk_respostas PRIMARY KEY (uuid_resposta)
);

ALTER TABLE alternativas
    ADD CONSTRAINT FK_ALTERNATIVAS_ON_UUID_PERGUNTA FOREIGN KEY (uuid_pergunta) REFERENCES perguntas (uuid_pergunta);

ALTER TABLE respostas
    ADD CONSTRAINT FK_RESPOSTAS_ON_UUID_ALTERNATIVA FOREIGN KEY (uuid_alternativa) REFERENCES alternativas (uuid_alternativa);

ALTER TABLE respostas
    ADD CONSTRAINT FK_RESPOSTAS_ON_UUID_JOGADOR FOREIGN KEY (uuid_jogador) REFERENCES jogadores (uuid_jogador);

ALTER TABLE respostas
    ADD CONSTRAINT FK_RESPOSTAS_ON_UUID_PERGUNTA FOREIGN KEY (uuid_pergunta) REFERENCES perguntas (uuid_pergunta);