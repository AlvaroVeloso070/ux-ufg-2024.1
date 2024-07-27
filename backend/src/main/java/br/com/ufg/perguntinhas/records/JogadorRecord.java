package br.com.ufg.perguntinhas.records;

import br.com.ufg.perguntinhas.infra.data.Jogador;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record JogadorRecord(
        String nome,
        String uuid,
        Double pontuacao,
        Double recordePontuacao
) {
    public static JogadorRecord toRecord(Jogador jogador) {
        return new JogadorRecord(
                jogador.getUuid().toString(),
                jogador.getNome(),
                jogador.getPontuacao(),
                jogador.getRecordePontuacao()
        );
    }

    public Jogador toEntity() {
        return Jogador.builder()
                .nome(nome)
                .uuid(getUuid())
                .pontuacao(pontuacao==null?0:pontuacao)
                .recordePontuacao(recordePontuacao==null?0:recordePontuacao)
                .build();
    }

    private UUID getUuid() {
        try {
            return UUID.fromString(uuid);
        } catch (Exception e) {
            return null;
        }
    }
}
