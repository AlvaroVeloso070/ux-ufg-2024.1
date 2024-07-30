package br.com.ufg.perguntinhas.infra.impl;

import br.com.ufg.perguntinhas.infra.JogadorRepository;
import br.com.ufg.perguntinhas.infra.data.repository.JogadorDataRepository;
import br.com.ufg.perguntinhas.records.JogadorRecord;
import br.com.ufg.perguntinhas.records.PontuacaoRecord;
import br.com.ufg.perguntinhas.records.PontuacaoRelativa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Repository
@Transactional
@RequiredArgsConstructor
public class JogadorRepositoryImpl implements JogadorRepository {

    private final JogadorDataRepository jogadorDataRepository;

    @Override
    public UUID save(JogadorRecord jogador) {
        return jogadorDataRepository.save(jogador.toEntity()).getUuid();
    }

    @Override
    public void updatePontuacaoJogador(String uuid, Double pontuacao) {

        var jogador = jogadorDataRepository.findById(UUID.fromString(uuid)).orElse(null);

        if (jogador != null) {
            jogador.setPontuacao(jogador.getPontuacao() + pontuacao);

            if (jogador.getPontuacao() > jogador.getRecordePontuacao()){
                jogador.setRecordePontuacao(jogador.getPontuacao());
            }

            jogadorDataRepository.save(jogador);
        }
    }

    @Override
    public List<PontuacaoRecord> buscarRanking() {
        return jogadorDataRepository.buscarRanking().stream().map(PontuacaoRecord::toRankRecord).toList();
    }

    @Override
    public PontuacaoRelativa buscarPontuacaoJogador(String uuid) {
        var list = jogadorDataRepository.buscarRanking();

        return IntStream.range(0, list.size())
                .filter(i -> list.get(i).getUuid().toString().equals(uuid))
                .mapToObj(i -> new PontuacaoRelativa(
                        list.get(i).getUuid().toString(),
                        list.get(i).getNome(),
                        list.get(i).getPontuacao(),
                        i))
                .findFirst()
                .orElse(null);
    }
}
