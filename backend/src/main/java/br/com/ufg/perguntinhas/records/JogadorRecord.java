package br.com.ufg.perguntinhas.records;

import br.com.ufg.perguntinhas.infra.data.Jogador;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record JogadorRecord(
        String nome,
        String uuid
) {
    public Jogador toEntity() {
        return Jogador.builder()
                .nome(nome)
                .uuid(UUID.fromString(uuid))
                .build();
    }
}
