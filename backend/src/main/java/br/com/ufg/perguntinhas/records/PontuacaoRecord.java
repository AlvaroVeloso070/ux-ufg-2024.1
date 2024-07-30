package br.com.ufg.perguntinhas.records;

import br.com.ufg.perguntinhas.infra.data.Jogador;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PontuacaoRecord(String uuid, String nomeJogador, Double pontuacao) {

    public static PontuacaoRecord toRankRecord(Jogador jogador) {
        return new PontuacaoRecord(
                jogador.getUuid().toString(),
                jogador.getNome(),
                jogador.getRecordePontuacao()
        );
    }
}
