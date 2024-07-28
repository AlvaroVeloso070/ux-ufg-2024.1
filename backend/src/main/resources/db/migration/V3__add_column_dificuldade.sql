-- Migração v3: Adição de tabela de dificuldades e ajustes nas tabelas existentes

-- Criação da tabela de dificuldades
CREATE TABLE dificuldades
(
    nivel VARCHAR PRIMARY KEY,
    pontuacao INT NOT NULL
);

-- Inserção dos valores na tabela de dificuldades
INSERT INTO dificuldades (nivel, pontuacao) VALUES ('FACIL', 100);
INSERT INTO dificuldades (nivel, pontuacao) VALUES ('MEDIO', 200);
INSERT INTO dificuldades (nivel, pontuacao) VALUES ('DIFICIL', 300);
INSERT INTO dificuldades (nivel, pontuacao) VALUES ('MUITO_DIFICIL', 400);

-- Ajuste na tabela de perguntas para adicionar a referência à tabela de dificuldades
ALTER TABLE perguntas
    ADD CONSTRAINT fk_perguntas_dificuldade FOREIGN KEY (dificuldade) REFERENCES dificuldades(nivel);

-- Trigger para atualizar a pontuação do jogador quando ele responde corretamente
CREATE OR REPLACE FUNCTION atualizar_pontuacao()
RETURNS TRIGGER AS $$
BEGIN
    IF EXISTS (SELECT 1 FROM alternativas WHERE uuid_alternativa = NEW.uuid_alternativa AND correta = TRUE) THEN
        UPDATE jogadores
        SET pontuacao = pontuacao + (SELECT pontuacao FROM dificuldades WHERE nivel = (SELECT dificuldade FROM perguntas WHERE uuid_pergunta = NEW.uuid_pergunta))
        WHERE uuid_jogador = NEW.uuid_jogador;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_atualizar_pontuacao
AFTER INSERT ON respostas
FOR EACH ROW
EXECUTE FUNCTION atualizar_pontuacao();