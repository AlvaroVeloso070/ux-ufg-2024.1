package br.com.ufg.perguntinhas.infra;

import br.com.ufg.perguntinhas.records.JogadorRecord;
import br.com.ufg.perguntinhas.records.PontuacaoRecord;
import br.com.ufg.perguntinhas.records.PontuacaoRelativa;

import java.util.List;
import java.util.UUID;

public interface JogadorRepository {

    UUID save(JogadorRecord jogador);

    void updatePontuacaoJogador(String uuid, Double pontuacao);

    List<PontuacaoRecord> buscarRanking();

    PontuacaoRelativa buscarPontuacaoJogador(String uuid);

    Long findRankingByUuidJogador(UUID uuidJogador);
}
