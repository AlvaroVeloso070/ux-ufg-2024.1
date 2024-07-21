package br.com.ufg.perguntinhas.records;

import br.com.ufg.perguntinhas.infra.data.Alternativa;
import br.com.ufg.perguntinhas.infra.data.Jogador;
import br.com.ufg.perguntinhas.infra.data.Resposta;

import java.util.UUID;

public record RespostaRecord(String uuidJogador,
                             String uuidAlternativa,
                             Long tempoResposta)
{
    public Resposta toEntity() {
        return Resposta.builder()
                .jogador(getJogador())
                .alternativaRespondida(getAlternativa())
                .tempoResposta(tempoResposta)
                .build();
    }

    private Jogador getJogador() {
        return Jogador.builder()
                .uuid(UUID.fromString(uuidJogador))
                .build();
    }

    private Alternativa getAlternativa() {
        return Alternativa.builder()
                .uuid(UUID.fromString(uuidAlternativa))
                .build();
    }
}
