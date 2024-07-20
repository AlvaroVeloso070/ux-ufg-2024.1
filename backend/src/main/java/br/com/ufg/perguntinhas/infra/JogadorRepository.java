package br.com.ufg.perguntinhas.infra;

import br.com.ufg.perguntinhas.records.JogadorRecord;

import java.util.UUID;

public interface JogadorRepository {
    UUID save(JogadorRecord jogador);
}
